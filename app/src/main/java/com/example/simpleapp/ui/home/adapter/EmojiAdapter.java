package com.example.simpleapp.ui.home.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.simpleapp.databinding.EmojiLayoutBinding;
import com.example.simpleapp.ui.home.model.EmojiModel;

import java.util.List;

public class EmojiAdapter extends RecyclerView.Adapter<EmojiAdapter.ViewHolder> {
    EmojiLayoutBinding binding;
    Context context;
    List<EmojiModel> list;

    public EmojiAdapter(Context context, List<EmojiModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public EmojiAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = EmojiLayoutBinding.inflate(LayoutInflater.from(context),parent,false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull EmojiAdapter.ViewHolder holder, int position) {
        EmojiModel model = list.get(position);
        holder.binding.ivEmoji.setImageResource(model.getIvUser());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        EmojiLayoutBinding binding;
        public ViewHolder(@NonNull EmojiLayoutBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
