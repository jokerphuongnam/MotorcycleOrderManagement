package com.example.motorcycleordermanagement.ui.edit.motorcycle;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.lifecycle.ViewModelProvider;

import com.example.motorcycleordermanagement.R;
import com.example.motorcycleordermanagement.databinding.FragmentEditMotorcycleBinding;
import com.example.motorcycleordermanagement.model.database.domain.Motorcycle;
import com.example.motorcycleordermanagement.ui.base.BaseFragment;
import com.example.motorcycleordermanagement.ui.edit.EditViewModel;
import com.example.schoolappliancesmanager.util.Resource;

import dagger.hilt.android.AndroidEntryPoint;

import static com.example.motorcycleordermanagement.ui.edit.EditActivity.DATA;

@AndroidEntryPoint
public class EditMotorcycleFragment extends BaseFragment<FragmentEditMotorcycleBinding, EditMotorcycleViewModel> {
    public EditMotorcycleFragment() {
        super(R.layout.fragment_edit_motorcycle);
    }

    public EditViewModel getActivityViewModel() {
        if(activityViewModel == null){
            activityViewModel = new ViewModelProvider(requireActivity()).get(EditViewModel.class);
        }
        return activityViewModel;
    }

    private EditViewModel activityViewModel;


    private final ActivityResultLauncher<Intent> imageChoose = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), (result) -> {
        if (result.getResultCode() == Activity.RESULT_OK) {
            Uri selectedImageUri = result.getData().getData();
            String path = selectedImageUri.toString();
            binding.getMotorcycle().setImage(path);
            binding.image.setImageURI(selectedImageUri);
            binding.imageLayout.setDisplayedChild(1);
        }
    });

    @Override
    public void createView() {
        viewModel.initMotorcycle((Motorcycle) getActivity().getIntent().getSerializableExtra(DATA));
        binding.addImage.setOnClickListener((v) -> {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.setType("image/*");
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            imageChoose.launch(intent);
        });
        binding.deleteImage.setOnClickListener((v) -> {
            binding.getMotorcycle().setImage("");
            binding.imageLayout.setDisplayedChild(0);
        });
        binding.success.setOnClickListener((v) -> {
            Motorcycle motorcycle = binding.getMotorcycle();
            motorcycle.setCapacity(Integer.parseInt(binding.capacity.getText().toString()));
            motorcycle.setCount(Integer.parseInt(binding.count.getText().toString()));
            if (motorcycle.getName().isEmpty()) {
                binding.motorcycleError.setVisibility(View.VISIBLE);
                binding.motorcycleError.setText(R.string.name_empty);
                return;
            }
            if (motorcycle.getCapacity() == 0 || binding.capacity.getText().toString().isEmpty()) {
                binding.motorcycleError.setVisibility(View.VISIBLE);
                binding.motorcycleError.setText(R.string.capacity_greater_than_zero);
                return;
            }
            if (motorcycle.getCount() == 0 || binding.count.getText().toString().isEmpty()) {
                binding.motorcycleError.setVisibility(View.VISIBLE);
                binding.motorcycleError.setText(R.string.count_greater_than_zero);
                return;
            }
            viewModel.setMotorcycle(motorcycle);
            switch (getActivityViewModel().getTypeAction()) {
                case ADD:
                    viewModel.addMotocycle();
                    break;
                case EDIT:
                    viewModel.editMotocycle();
                    break;
            }
        });
        viewModel.getSuccess().observe(getViewLifecycleOwner(), (success) -> {
            getActivity().finish();
        });
        viewModel.getSuccess().observe(getViewLifecycleOwner(), (resource) -> {
            if (resource instanceof Resource.Loading) {
                binding.motorcycleError.setVisibility(View.GONE);
            } else if (resource instanceof Resource.Success) {
                getActivity().finish();
                showToast(getString(R.string.save_success));
            } else if (resource instanceof Resource.Error) {
                binding.motorcycleError.setText(R.string.save_fail);
                binding.motorcycleError.setVisibility(View.VISIBLE);
            }
        });
        if (viewModel.getMotorcycle().getImage() != null &&!viewModel.getMotorcycle().getImage().isEmpty()) {
            binding.imageLayout.setDisplayedChild(1);
            Uri uri = Uri.parse(viewModel.getMotorcycle().getImage());
            binding.image.setImageURI(uri);
        }
    }

    @Override
    public void onResume() {
        super.onResume();
        binding.setMotorcycle(viewModel.getMotorcycle());
    }

    @Override
    public EditMotorcycleViewModel getViewModel() {
        return new ViewModelProvider(this).get(EditMotorcycleViewModel.class);
    }
}
