package com.example.mad.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.mad.Helper.TinyDB;
import com.example.mad.R;
import com.example.mad.databinding.ActivityOrderCompleteBinding;

public class OrderCompleteActivity extends AppCompatActivity {
    ActivityOrderCompleteBinding binding;

    private ImageView gifIm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_order_complete);
        binding = ActivityOrderCompleteBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        Glide.with(this)
                .load(R.drawable.completed)
                .into(binding.placeOrder);



        new Handler().postDelayed(() -> {
            clearCart();
            startActivity(new Intent(OrderCompleteActivity.this, HeroPage.class));
            finish();
        },3000);
    }

    private void clearCart() {
        TinyDB tinyDB = new TinyDB(this); // Pass appropriate context here
        tinyDB.clear(); // Clear all stored data, including cart items
    }
}