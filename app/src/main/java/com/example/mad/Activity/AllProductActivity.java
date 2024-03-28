package com.example.mad.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;
import android.view.Window;

import com.example.mad.Adapter.AllProdAdapter;
import com.example.mad.R;
import com.example.mad.databinding.ActivityAllProductBinding;
import com.example.mad.domain.ChangeDomain;

import java.util.ArrayList;

public class AllProductActivity extends AppCompatActivity {

    ActivityAllProductBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAllProductBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        statusBarColor();
        initRecyclerView();
    }

    private void statusBarColor() {
        Window window = AllProductActivity.this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(AllProductActivity.this,R.color.lightGreen));
    }

    private void initRecyclerView() {
        ArrayList<ChangeDomain> items = new ArrayList<>();
        items.add(new ChangeDomain("ABS Black","black_abs",14,4.3,750,
                "Engineered for durability, heat resistance, and precise printing. Ideal for creating functional\n" +
                        "prototypes and end-use parts with exceptional strength and reliability."));
        items.add(new ChangeDomain("ABS Blue","blue_abs",13,3.4,2000,
                "Engineered for durability, heat resistance, and precise printing. Ideal for creating functional\n" +
                        "prototypes and end-use parts with exceptional strength and reliability."));
        items.add(new ChangeDomain("ABS Green","green_abs",6,4.1,2000,
                "Engineered for durability, heat resistance, and precise printing. Ideal for creating functional\n" +
                        "prototypes and end-use parts with exceptional strength and reliability."));
        items.add(new ChangeDomain("ABS Red","red_abs",0,4.8,1500,
                "Engineered for durability, heat resistance, and precise printing. Ideal for creating functional\n" +
                        "prototypes and end-use parts with exceptional strength and reliability."));
        items.add(new ChangeDomain("ABS White","white_abs",7,3.6,750,
                "Engineered for durability, heat resistance, and precise printing. Ideal for creating functional\n" +
                        "prototypes and end-use parts with exceptional strength and reliability."));
        items.add(new ChangeDomain("PLA Black","black_pla",12,4.9,500,
                "Eco-friendly 3D printing with precise detail and vibrant colors, perfect for prototypes and\n" +
                        "sustainable creations. effortless printing on most FDM printers, delivering smooth finishes and\n" +
                        "low warping for versatile applications"));
        items.add(new ChangeDomain("PLA Blue","blue_pla",21,3.5,500,
                "Eco-friendly 3D printing with precise detail and vibrant colors, perfect for prototypes and\n" +
                        "sustainable creations. effortless printing on most FDM printers, delivering smooth finishes and\n" +
                        "low warping for versatile applications"));
        items.add(new ChangeDomain("PLA Green","green_pla",15,4.8,1000,
                "Eco-friendly 3D printing with precise detail and vibrant colors, perfect for prototypes and\n" +
                        "sustainable creations. effortless printing on most FDM printers, delivering smooth finishes and\n" +
                        "low warping for versatile applications"));
        items.add(new ChangeDomain("PLA White","white_pla",15,3.5,500,
                "Eco-friendly 3D printing with precise detail and vibrant colors, perfect for prototypes and\n" +
                        "sustainable creations. effortless printing on most FDM printers, delivering smooth finishes and\n" +
                        "low warping for versatile applications"));
        items.add(new ChangeDomain("PLA Red","red_pla",4,4,2000,
                "Eco-friendly 3D printing with precise detail and vibrant colors, perfect for prototypes and\n" +
                        "sustainable creations. effortless printing on most FDM printers, delivering smooth finishes and\n" +
                        "low warping for versatile applications"));
//        items.add(new ChangeDomain("Carbon Fiber Black","black_carbonfibre",7,3.4,1500,
//                "Delivering unparalleled strength, rigidity, and lightweight properties for advanced\n" +
//                        "3D printing. Perfect for engineering prototypes and high-performance parts, its composite\n" +
//                        "construction ensures exceptional durability and precision."));
//        items.add(new ChangeDomain("Carbon Fiber Grey","grey_carbonfibre",20,4,2000,
//                "Delivering unparalleled strength, rigidity, and lightweight properties for advanced\n" +
//                        "3D printing. Perfect for engineering prototypes and high-performance parts, its composite\n" +
//                        "construction ensures exceptional durability and precision."));



        binding.allProd.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        binding.allProd.setAdapter(new AllProdAdapter(items));

        binding.backHome.setOnClickListener(v -> finish());
    }
}