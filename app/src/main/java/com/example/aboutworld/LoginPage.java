package com.example.aboutworld;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class LoginPage extends AppCompatActivity {
    TextView btnSignUp;
    ImageView img;
    TextView txtTitle, txtSub;
    TextInputLayout txtUser, txtPass;
    LinearLayout txtSignUp;
    Button btnLogin, btnForgotPass;

    SharedPreferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FIRST_APPLICATION_WINDOW);
        setContentView(R.layout.activity_login_page);

        img = findViewById(R.id.img_logo);
        txtTitle = findViewById(R.id.tv_titleLogin);
        txtSub = findViewById(R.id.subtitleLogin);
        txtUser = findViewById(R.id.username_login);
        txtPass = findViewById(R.id.password_login);
        btnLogin = findViewById(R.id.btn_login);
        btnForgotPass = findViewById(R.id.btn_forgotPass);
        btnSignUp = findViewById(R.id.btn_signUp);
        txtSignUp = findViewById(R.id.ll_signup);

        preferences = getSharedPreferences(name:"userInfo", mode:0);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(packageContect:LoginPage.this, SignUp.class);
                startActivity(intent);
            }
        });

        txtPass.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN && keyCode = KeyEvent.KEYCODE_ENTER) {
                    String userValue = txtUser.getEditText().getText().toString();
                    String emailValue = txtUser.getEditText().getText().toString();
                    String passValue = txtPass.getEditText().getText().toString();

                    String loginUser = preferences.getString(s:"user", s1:"");
                    String emailUser = preferences.getString("email", s1:"");
                    String loginPass = preferences.getString(s:"pass", s1:"");

                    if (userValue.equals(loginUser) && passValue.equals(loginPass) || emailValue.equals(emailUser) && passValue.equals(loginPass)) {
                        Intent intent = new Intent(packageContext:LoginPage.this, Dashboard.class)
                        startActivity(intent);
                        finish();
                        Toast.makeText(context:LoginPage.this, text:"Login", Toast.LENGTH_LONG).
                        show();
                    } else {
                        Toast.makeText(context:LoginPage.this, text:
                        "Username or Password doesn't match", Toast.LENGTH_LONG).show();
                    }
                }
                return false;
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userValue = txtUser.getEditText().getText().toString();
                String emailValue = txtUser.getEditText().getText().toString();
                String passValue = txtPass.getEditText().getText().toString();

                String loginUser = preferences.getString(s:"user", s1:"");
                String emailUser = preferences.getString(s:"email", s1:"");
                String loginPass = preferences.getString(s:"pass", s1:"");
                if (userValue.equals(loginUser) && passValue.equals(loginPass) || emailValue.equals(emailUser) && passValue.equals(loginPass)){
                    Intent intent = new Intent(packageContext:LoginPage.this, Dashboard.class);
                    startActivity(intent);
                    finish();
                    Toast.makeText(context:LoginPage.this, text:"Login", Toast.LENGTH_LONG).show();
                }else{
                    Toast.makeText(context:LoginPage.this, text:
                    "Username or Password doesn't match", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnForgotPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                Intent intent = new Intent(packageContext:LoginPage.this, ForgotPass.class);
                startActivity(intent);
            }
        });
    }
}