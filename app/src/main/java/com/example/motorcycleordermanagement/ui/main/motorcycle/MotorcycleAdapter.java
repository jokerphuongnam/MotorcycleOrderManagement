package com.example.motorcycleordermanagement.ui.main.motorcycle;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.motorcycleordermanagement.R;
import com.example.motorcycleordermanagement.databinding.ItemMotorcycleBinding;
import com.example.motorcycleordermanagement.model.database.domain.Motorcycle;
import com.example.motorcycleordermanagement.utils.ItemClickRecycler;

public class MotorcycleAdapter extends ListAdapter<Motorcycle, MotorcycleAdapter.ViewHolder> {
    private final ItemClickRecycler<Motorcycle> itemClickRecycler;

    public MotorcycleAdapter(ItemClickRecycler<Motorcycle> itemClickRecycler) {
        super(getDiffCallback());
        this.itemClickRecycler = itemClickRecycler;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return ViewHolder.create(parent, viewType, itemClickRecycler);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(getItem(position));
    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {

        @NonNull
        private final ItemMotorcycleBinding binding;
        @NonNull
        private final Context context;
        private final ItemClickRecycler<Motorcycle> itemClickRecycler;

        public ViewHolder(@NonNull ItemMotorcycleBinding binding, ItemClickRecycler<Motorcycle> itemClickRecycler, @NonNull Context context) {
            super(binding.getRoot());
            this.binding = binding;
            this.itemClickRecycler = itemClickRecycler;
            this.context = context;
        }

        public void bind(Motorcycle motorcycle) {
            binding.setMotorcycle(motorcycle);

            binding.container.setOnClickListener((v) -> {
                itemClickRecycler.edit(motorcycle);
            });
            binding.container.setOnLongClickListener((v) -> {
                PopupMenu popupMenu = new PopupMenu(context, v);
                popupMenu.getMenuInflater().inflate(R.menu.menu_option_item, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener((menu) -> {
                    switch (menu.getItemId()) {
                        case R.id.edit:
                            itemClickRecycler.edit(motorcycle);
                            break;
                        case R.id.delete:
                            itemClickRecycler.delete(motorcycle);
                    }
                    return true;
                });
                popupMenu.show();
                return true;
            });
        }

        @NonNull
        static ViewHolder create(@NonNull ViewGroup parent, int viewType, ItemClickRecycler<Motorcycle> itemClickRecycler) {
            return new ViewHolder(ItemMotorcycleBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false), itemClickRecycler, parent.getContext());
        }
    }

    private static DiffUtil.ItemCallback<Motorcycle> diffCallback = null;

    private static DiffUtil.ItemCallback<Motorcycle> getDiffCallback() {
        if (diffCallback == null) {
            diffCallback = new DiffUtil.ItemCallback<Motorcycle>() {
                @Override
                public boolean areItemsTheSame(@NonNull Motorcycle oldItem, @NonNull Motorcycle newItem) {
                    return oldItem.getMotorcycleId() == newItem.getMotorcycleId();
                }

                @Override
                public boolean areContentsTheSame(@NonNull Motorcycle oldItem, @NonNull Motorcycle newItem) {
                    return oldItem.equals(newItem);
                }
            };
        }
        return diffCallback;
    }
}
