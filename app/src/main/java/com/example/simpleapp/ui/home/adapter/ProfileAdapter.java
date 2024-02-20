package com.example.simpleapp.ui.home.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simpleapp.databinding.EmojiLayoutBinding;
import com.example.simpleapp.databinding.ProfileLayoutBinding;
import com.example.simpleapp.ui.home.model.EmojiModel;
import com.example.simpleapp.ui.home.model.ProfileModel;

import java.util.List;

public class ProfileAdapter extends RecyclerView.Adapter<ProfileAdapter.ViewHolder> {
    ProfileLayoutBinding binding;
    private int selectedItem = RecyclerView.NO_POSITION;
    Context context;
    List<ProfileModel> list;
    public ProfileAdapter(Context context, List<ProfileModel> list) {
        this.context = context;
        this.list = list;
    }
    @NonNull
    @Override
    public ProfileAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ProfileLayoutBinding.inflate(LayoutInflater.from(context),parent,false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProfileAdapter.ViewHolder holder, int position) {
        ProfileModel model = list.get(position);
        holder.binding.ivUser.setImageResource(model.getIvUser());
        holder.binding.tvUserName.setText(model.getTvUser());
        if(position==0){
            holder.binding.ivGift.setVisibility(View.VISIBLE);
        }
        else {
            holder.binding.ivGift.setVisibility(View.GONE);
        }
        if (position == selectedItem) {
            holder.binding.tvUserName.setTextColor(Color.RED); // Set selected item color
        } else {
            holder.binding.tvUserName.setTextColor(Color.BLACK); // Set default item color
        }
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                if (position != RecyclerView.NO_POSITION) {
                    // Update selected item position
                    selectedItem = position;
                    notifyDataSetChanged(); // Notify adapter to rebind all items
                }


            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ProfileLayoutBinding binding;
        public ViewHolder(@NonNull ProfileLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
