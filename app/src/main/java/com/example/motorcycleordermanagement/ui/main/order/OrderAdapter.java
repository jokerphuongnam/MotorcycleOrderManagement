package com.example.motorcycleordermanagement.ui.main.order;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.PopupMenu;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.motorcycleordermanagement.R;
import com.example.motorcycleordermanagement.databinding.ItemOrderBinding;
import com.example.motorcycleordermanagement.model.database.domain.Order;
import com.example.motorcycleordermanagement.utils.ItemClickRecycler;

public class OrderAdapter extends ListAdapter<Order, OrderAdapter.ViewHolder> {
    private final ItemClickRecycler<Order> itemClickRecycler;

    public OrderAdapter(ItemClickRecycler<Order> itemClickRecycler) {
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
        private final ItemOrderBinding binding;
        private final Context context;
        private final ItemClickRecycler<Order> itemClickRecycler;

        public ViewHolder(@NonNull ItemOrderBinding binding, ItemClickRecycler<Order> itemClickRecycler, @NonNull Context context) {
            super(binding.getRoot());
            this.binding = binding;
            this.itemClickRecycler = itemClickRecycler;
            this.context = context;
        }

        public void bind(Order order) {
            binding.setOrder(order);

            binding.container.setOnClickListener((v) -> {
                itemClickRecycler.edit(order);
            });
            binding.container.setOnLongClickListener((v) -> {
                PopupMenu popupMenu = new PopupMenu(context, v);
                popupMenu.getMenuInflater().inflate(R.menu.menu_option_item, popupMenu.getMenu());
                popupMenu.setOnMenuItemClickListener((menu) -> {
                    switch (menu.getItemId()) {
                        case R.id.edit:
                            itemClickRecycler.edit(order);
                            break;
                        case R.id.delete:
                            itemClickRecycler.delete(order);
                    }
                    return true;
                });
                popupMenu.show();
                return true;
            });
        }

        @NonNull
        static ViewHolder create(@NonNull ViewGroup parent, int viewType, ItemClickRecycler<Order> itemClickRecycler) {
            return new ViewHolder(ItemOrderBinding.inflate(LayoutInflater.from(parent.getContext()), parent, false), itemClickRecycler, parent.getContext());
        }
    }

    private static DiffUtil.ItemCallback<Order> diffCallback = null;

    private static DiffUtil.ItemCallback<Order> getDiffCallback() {
        if (diffCallback == null) {
            diffCallback = new DiffUtil.ItemCallback<Order>() {
                @Override
                public boolean areItemsTheSame(@NonNull Order oldItem, @NonNull Order newItem) {
                    return oldItem.getOrderId() == newItem.getOrderId();
                }

                @Override
                public boolean areContentsTheSame(@NonNull Order oldItem, @NonNull Order newItem) {
                    return oldItem.equals(newItem);
                }
            };
        }
        return diffCallback;
    }
}
