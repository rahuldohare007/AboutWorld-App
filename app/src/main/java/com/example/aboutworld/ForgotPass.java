package com.example.aboutworld;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class ForgotPass extends AppCompatActivity {
    TextInputLayout inpUser;
    Button btnFind;
    TextView btnSignUp;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_pass);

        inpUser = findViewById(R.id.username_find);
        btnFind = findViewById(R.id.btn_Find);
        btnSignUp = findViewById(R.id.btn_signUp);

        preferences = getSharedPreferences(name:"userInfo", mode:0);

        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userValue = inpUser.getEditText().getText().toString();
                String emailValue = inpUser.getEditText().getText().toString();

                String loginUser = preferences.getString("user", s1:"");
                String emailUser = preferences.getString("email", "");

                if (userValue.equals(loginUser) || emailValue.equals(emailUser)) {
                    Toast.makeText(context:ForgotPass.this, text:
                    "Successfully Find Your Account", Toast.LENGTH_LONG).show();
                    finish();
                    Intent intent = new Intent(packageContext:ForgotPass.this, EditUser.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(context:ForgotPass.this, text:
                    "Username or Email not Found", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(packageContext:ForgotPass.this, SignUp.class);
                startActivity(intent);
            }
        });
    }
}