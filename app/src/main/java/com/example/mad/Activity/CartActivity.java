package com.example.mad.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;

import com.example.mad.Adapter.CartAdapter;
import com.example.mad.Helper.ManagmentCart;
import com.example.mad.R;
import com.example.mad.databinding.ActivityCartBinding;

public class CartActivity extends AppCompatActivity {

    private ManagmentCart managmentCart;
    ActivityCartBinding binding;
    double Tax;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCartBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        managmentCart = new ManagmentCart(this);
        setVariable();
        initList();
        statusBarColor();
        calculatorCart();
        endPage();
    }

    private void endPage() {
        binding.purchaseDone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CartActivity.this, OrderCompleteActivity.class));
            }
        });
    }

    private void statusBarColor() {
        Window window = CartActivity.this.getWindow();
        window.setStatusBarColor(ContextCompat.getColor(CartActivity.this,R.color.white));
    }

    private void initList() {
        if (managmentCart.getListCart().isEmpty()){
            binding.emptyTxt.setVisibility(View.VISIBLE);
            binding.scrollCart.setVisibility(View.GONE);
        } else {
            binding.emptyTxt.setVisibility(View.GONE);
            binding.scrollCart.setVisibility(View.VISIBLE);
        }
        binding.cartView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        binding.cartView.setAdapter(new CartAdapter(managmentCart.getListCart(), () -> calculatorCart()));
    }

    private void calculatorCart(){
        double percntTax = 0.02;
        double delivery = 10;
        Tax = Math.round(managmentCart.getTotalFee()*percntTax*100)/100;
        double Total = Math.round((managmentCart.getTotalFee()+Tax+delivery)*100)/100;
        double itemTotal = Math.round(managmentCart.getTotalFee()*100)/100;
        binding.totalFeeTxt.setText("Rs."+itemTotal);
        binding.taxTxt.setText("Rs."+Tax);
        binding.deliveryTxt.setText("Rs."+delivery);
        binding.totalTxt.setText("Rs."+Total);
    }
    private void setVariable() {
        binding.backBtn.setOnClickListener(v -> finish());
    }
}