package com.example.mad.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.GranularRoundedCorners;
import com.example.mad.Activity.ProductPage;
import com.example.mad.databinding.ViewallprodPupListBinding;
import com.example.mad.domain.ChangeDomain;

import java.util.ArrayList;

public class AllProdAdapter extends RecyclerView.Adapter<AllProdAdapter.Viewholder> {
    ArrayList<ChangeDomain> items;
    Context context;
    ViewallprodPupListBinding binding;

    public AllProdAdapter(ArrayList<ChangeDomain> items) {
        this.items = items;
    }

    @NonNull
    @Override
    public AllProdAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        binding = ViewallprodPupListBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        context = parent.getContext();
        return new Viewholder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull AllProdAdapter.Viewholder holder, int position) {
        binding.titleTxt.setText(items.get(position).getTitle());
        binding.priceTxt.setText("Rs." + items.get(position).getPrice());
        binding.reviewTxt.setText(""+items.get(position).getReview());
        binding.ratingTxt.setText(""+items.get(position).getScore());

        int drawableResourced = holder.itemView.getResources().getIdentifier(items.get(position).getPicUrl(),"drawable",holder.itemView.getContext().getPackageName());
        Glide.with(context)
                .load(drawableResourced)
                .transform(new GranularRoundedCorners(30,30,0,0))
                .into(binding.itemPic);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ProductPage.class);
            intent.putExtra("object",items.get(position));
            context.startActivity(intent);

        });
    }

    @Override
    public int getItemCount() {
        return items.size();
    }

    public class Viewholder extends RecyclerView.ViewHolder{
        public Viewholder(ViewallprodPupListBinding binding) {
            super(binding.getRoot());
        }
    }
}
