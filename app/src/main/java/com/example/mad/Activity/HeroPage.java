package com.example.mad.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import com.example.mad.Adapter.PopularAdapter;
import com.example.mad.R;
import com.example.mad.databinding.ActivityHeroPageBinding;
import com.example.mad.domain.ChangeDomain;

import java.util.ArrayList;

public class HeroPage extends AppCompatActivity {

    ActivityHeroPageBinding binding;
    private TextView allProd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityHeroPageBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        statusBarColor();
        initRecyclerView();
        allProd = findViewById(R.id.seeAllProd);
        allProd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(HeroPage.this, AllProductActivity.class));
            }
        });

        bottomNavigation();
    }

    private void bottomNavigation() {
        binding.goToCart.setOnClickListener(v -> startActivity(new Intent(HeroPage.this,CartActivity.class)));
    }

    private void statusBarColor() {
        Window window = HeroPage.this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(HeroPage.this,R.color.lightGreen));
    }

    private void initRecyclerView() {
        ArrayList<ChangeDomain> items = new ArrayList<>();
        items.add(new ChangeDomain("Carbon Fiber Black","black_carbonfibre",7,3.4,1500,
                "Delivering unparalleled strength, rigidity, and lightweight properties for advanced\n" +
                        "3D printing. Perfect for engineering prototypes and high-performance parts, its composite\n" +
                        "construction ensures exceptional durability and precision."));
        items.add(new ChangeDomain("Carbon Fiber Grey","grey_carbonfibre",20,4,2000,
                "Delivering unparalleled strength, rigidity, and lightweight properties for advanced\n" +
                        "3D printing. Perfect for engineering prototypes and high-performance parts, its composite\n" +
                        "construction ensures exceptional durability and precision."));
        items.add(new ChangeDomain("FLEX Black","black_flex",7,3.4,1500,
                "Introducing our flexible filament, designed for 3D printing with exceptional elasticity and\n" +
                        "durability. Perfect for producing bendable prototypes and wearable items."));
        items.add(new ChangeDomain("FLEX Blue","blue_flex",20,4,2000,
                "Introducing our flexible filament, designed for 3D printing with exceptional elasticity and\n" +
                        "durability. Perfect for producing bendable prototypes and wearable items."));

        binding.PopularView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false));
        binding.PopularView.setAdapter(new PopularAdapter(items));
    }
}