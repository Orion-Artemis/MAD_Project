package com.example.mad.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.Window;

import com.bumptech.glide.Glide;
import com.example.mad.Helper.ManagmentCart;
import com.example.mad.R;
import com.example.mad.databinding.ActivityProductPageBinding;
import com.example.mad.domain.ChangeDomain;

public class ProductPage extends AppCompatActivity {
    private ActivityProductPageBinding binding;
    private ChangeDomain object;
    private int numOrder = 1;
    private ManagmentCart managmentCart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityProductPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getBundles();
        managmentCart = new ManagmentCart(this);
        statusBarColor();
    }
    private void statusBarColor() {
        Window window = ProductPage.this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(ProductPage.this, R.color.white));
    }

    private void getBundles() {
        object = (ChangeDomain) getIntent().getSerializableExtra("object");
        int drawableResourceId = this.getResources().getIdentifier(object.getPicUrl(),"drawable",this.getPackageName());
        Glide.with(this)
                .load(drawableResourceId)
                .into(binding.picture);

        binding.titleTxt.setText(object.getTitle());
        binding.priceTxt.setText("Rs."+object.getPrice());
        binding.descTxt.setText(object.getDesc());
        binding.reviewTxt.setText(object.getReview()+"");
        binding.ratingTxt.setText(object.getScore()+"");

        binding.addToCart.setOnClickListener(v -> {
            object.setNumberInCart(numOrder);
            managmentCart.insertFood(object);
        });

        binding.backBTN.setOnClickListener(v -> finish());
    }
}