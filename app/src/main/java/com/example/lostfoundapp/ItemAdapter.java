package com.example.lostfoundapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ItemAdapter extends RecyclerView.Adapter<ItemAdapter.ViewHolder> {
    interface OnDeleteClickListener {
        void onDelete(int id);
    }

    private final List<Item> items;
    private final OnDeleteClickListener deleteListener;

    public ItemAdapter(List<Item> items, OnDeleteClickListener listener) {
        this.items = items;
        this.deleteListener = listener;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Item item = items.get(position);
        holder.tvName.setText(item.getName());
        holder.tvStatus.setText(item.getStatus());
        holder.tvContact.setText(item.getPhone());
        holder.btnDelete.setOnClickListener(v -> deleteListener.onDelete(item.getId()));
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvName, tvStatus, tvContact;
        ImageButton btnDelete;

        ViewHolder(View view) {
            super(view);
            tvName = view.findViewById(R.id.tv_item_name);
            tvStatus = view.findViewById(R.id.tv_status);
            tvContact = view.findViewById(R.id.tv_contact);
            btnDelete = view.findViewById(R.id.btn_delete);
        }
    }
}
