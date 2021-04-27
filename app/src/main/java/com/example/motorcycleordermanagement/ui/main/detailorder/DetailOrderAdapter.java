package com.example.motorcycleordermanagement.ui.main.detailorder;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.motorcycleordermanagement.R;
import com.example.motorcycleordermanagement.databinding.ItemDetailOrderBinding;
import com.example.motorcycleordermanagement.model.database.domain.DetailOrder;
import com.example.motorcycleordermanagement.utils.ItemClickRecycler;

public class DetailOrderAdapter extends ListAdapter<DetailOrder, DetailOrderAdapter.ViewHolder> {
    private final ItemClickRecycler<DetailOrder> itemClickRecycler;

    public DetailOrderAdapter(ItemClickRecycler<DetailOrder> itemClickRecycler) {
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

    }

    protected static class ViewHolder extends RecyclerView.ViewHolder {

        @NonNull
        private final ItemDetailOrderBinding binding;
        @NonNull
        private final Context context;
        private final ItemClickRecycler<DetailOrder> itemClickRecycler;

        public ViewHolder(@NonNull ItemDetailOrderBinding binding, ItemClickRecycler<DetailOrder> itemClickRecycler, @NonNull Context context) {
            super(binding.getRoot());
            this.binding = binding;
            this.itemClickRecycler = itemClickRecycler;
            this.context = context;
        }

        public void bind(DetailOrder detailOrder) {
            binding.setDetailOrder(detailOrder);

            binding.container.setOnClickListener((v) -> {
                itemClickRecycler.edit(detailOrder);
            });
            binding.container.setOnLongClickListener((v) -> {
                PopupMenu popupMenu = new PopupMenu(context, v);
                popupMenu.getMenuInflater().inflate(R.menu.menu_option_item, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener((menu) -> {
                    switch (menu.getItemId()) {
                        case R.id.edit:
                            itemClickRecycler.edit(detailOrder);
                            break;
                        case R.id.delete:
                            itemClickRecycler.delete(detailOrder);
                    }
                    return true;
                });
                popupMenu.show();
                return true;
            });
        }

        @NonNull
        static ViewHolder create(@NonNull ViewGroup parent, int viewType, ItemClickRecycler<DetailOrder> itemClickRecycler) {
            return new ViewHolder(ItemDetailOrderBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false), itemClickRecycler, parent.getContext());
        }
    }

    private static DiffUtil.ItemCallback<DetailOrder> diffCallback = null;

    private static DiffUtil.ItemCallback<DetailOrder> getDiffCallback() {
        if (diffCallback == null) {
            diffCallback = new DiffUtil.ItemCallback<DetailOrder>() {
                @Override
                public boolean areItemsTheSame(@NonNull DetailOrder oldItem, @NonNull DetailOrder newItem) {
                    return oldItem.getOrderId() == newItem.getOrderId();
                }

                @Override
                public boolean areContentsTheSame(@NonNull DetailOrder oldItem, @NonNull DetailOrder newItem) {
                    return oldItem.equals(newItem);
                }
            };
        }
        return diffCallback;
    }
}
