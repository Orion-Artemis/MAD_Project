package com.example.mad.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.mad.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity2 extends AppCompatActivity {
    private Button signUp;
    private EditText name,email,phone,pass;
    private TextView loginRedirect;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        name = findViewById(R.id.nameIn);
        email = findViewById(R.id.emailIn);
        phone = findViewById(R.id.phoneIn);
        pass = findViewById(R.id.passIn);
        signUp = findViewById(R.id.signUpBt);
        loginRedirect = findViewById(R.id.loginRedirect);
        mAuth = FirebaseAuth.getInstance();

        signUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = name.getText().toString().trim();
                String cred = pass.getText().toString().trim();
                String num = phone.getText().toString().trim();
                String mail = email.getText().toString().trim();
                Bundle bundle = new Bundle();
                Intent intent = new Intent(getApplicationContext(), HeroPage.class);

                if (user.isEmpty() || cred.isEmpty() || num.isEmpty() || mail.isEmpty()){
                    Toast.makeText(MainActivity2.this,"Please Enter all Credentials",Toast.LENGTH_SHORT).show();
                } else {
                    mAuth.createUserWithEmailAndPassword(mail , cred).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(MainActivity2.this, "SignUp Successful", Toast.LENGTH_SHORT).show();
                                bundle.putString("Name",cred);
                                intent.putExtras(bundle);
                                startActivity(intent);
                            } else {
                                Toast.makeText(MainActivity2.this, "SignUp Failed Try Again", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        loginRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity2.this, LogInPage.class));
            }
        });
    }
    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            startActivity(new Intent(MainActivity2.this, HeroPage.class));
            finish();
        }
    }
}