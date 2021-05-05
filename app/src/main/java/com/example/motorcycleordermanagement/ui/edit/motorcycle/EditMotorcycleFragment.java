package com.example.motorcycleordermanagement.ui.edit.motorcycle;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.View;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import com.example.motorcycleordermanagement.R;
import com.example.motorcycleordermanagement.databinding.FragmentEditMotorcycleBinding;
import com.example.motorcycleordermanagement.model.database.domain.Motorcycle;
import com.example.motorcycleordermanagement.ui.base.BaseFragment;
import com.example.motorcycleordermanagement.ui.edit.EditViewModel;
import com.example.schoolappliancesmanager.util.Resource;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Calendar;

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

    private Uri image = null;

    private final ActivityResultLauncher<Intent> imageChoose = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), (result) -> {
        if (result.getResultCode() == Activity.RESULT_OK) {
            Uri selectedImageUri = result.getData().getData();
            image = selectedImageUri;
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
            image = null;
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
            if(image != null){
                Uri oldUri = Uri.parse(viewModel.getMotorcycle().getImage());
                deleteImage(oldUri);
                String path = saveImagePrivate(image).toString();
                binding.getMotorcycle().setImage(path);
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

    @NonNull
    private String fileName() {
        return String.valueOf(Calendar.getInstance().getTimeInMillis());
    }

    @NonNull
    private Uri saveImagePrivate(@NonNull Uri selectedImageUri) {
        FileOutputStream fos = null;
        InputStream iStream = null;
        String name = fileName();
        try {
            iStream = requireActivity().getContentResolver().openInputStream(selectedImageUri);
            byte[] inputData = getBytes(iStream);
            fos = requireActivity().openFileOutput(name, Context.MODE_PRIVATE);
            fos.write(inputData);
        } catch (Exception ex){
            ex.printStackTrace();
        } finally {
            try {
                fos.close();
                iStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        File file = new File(requireActivity().getFilesDir(), name);
        return Uri.fromFile(file);
    }

    public byte[] getBytes(@NonNull InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteBuffer = new ByteArrayOutputStream();
        int bufferSize = 1024;
        byte[] buffer = new byte[bufferSize];

        int len = 0;
        while ((len = inputStream.read(buffer)) != -1) {
            byteBuffer.write(buffer, 0, len);
        }
        return byteBuffer.toByteArray();
    }

    private void deleteImage(@NonNull Uri uri){
        File myFile = new File(uri.toString());
        if(myFile.exists()){
            myFile.delete();
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
