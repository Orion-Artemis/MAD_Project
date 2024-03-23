package com.example.mad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LogInPage extends AppCompatActivity {
    private Button logIn;
    private EditText name,email,phone,pass;
    private TextView signUpRedirect;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in_page);
        name = findViewById(R.id.nameIn);
        email = findViewById(R.id.emailIn);
        phone = findViewById(R.id.phoneIn);
        pass = findViewById(R.id.passIn);
        logIn = findViewById(R.id.logInBt);
        signUpRedirect = findViewById(R.id.signUpRedirect);
        mAuth = FirebaseAuth.getInstance();

        logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = name.getText().toString().trim();
                String cred = pass.getText().toString().trim();
                String num = phone.getText().toString().trim();
                String mail = email.getText().toString().trim();
                Intent intent = new Intent(getApplicationContext(), MainPage.class);
                Bundle bundle = new Bundle();

                if (user.isEmpty() || cred.isEmpty() || num.isEmpty() || mail.isEmpty()){
                    Toast.makeText(LogInPage.this,"Please Enter all Credentials",Toast.LENGTH_SHORT).show();
                } else {
                    mAuth.signInWithEmailAndPassword(mail, cred).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                Toast.makeText(LogInPage.this, "SignUp Successful", Toast.LENGTH_SHORT).show();
                                bundle.putString("Name",user);
                                bundle.putString("Number",num);
                                intent.putExtras(bundle);
                                startActivity(intent);
                            } else {
                                Toast.makeText(LogInPage.this, "SignUp Failed Try Again", Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        signUpRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LogInPage.this, MainActivity2.class));
            }
        });
    }
}