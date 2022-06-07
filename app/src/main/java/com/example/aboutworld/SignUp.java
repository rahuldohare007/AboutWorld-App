package com.example.aboutworld;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Intent;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
public class SignUp extends AppCompatActivity {
    TextView txtSignIn;

    ImageView img;
    TextView txtTitle, txtSub;
    TextInputLayout inpFullname, inpEmail, inpPhone, inpUser, inpPass, inpRePass;
    Button btnSignUp, btnReset;

    SharedPreferences preferences;

    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_USER = "user";
    private static final String KEY_PASS = "pass";
    private static final String KEY_REPASS = "repass";

    private static final String KEY_TOTAL_PRICE = "total_price";
    private static final String KEY_NAME_TOUR = "name_tour";
    private static final String KEY_COUNT_ITEMS = "count_items";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        img = findViewById(R.id.img_logo);
        txtTitle = findViewById(R.id.tv_title_regis);
        inpFullname = findViewById(R.id.name);
        inpEmail = findViewById(R.id.email);
        inpPhone = findViewById(R.id.phone);
        inpUser = findViewById(R.id.username_regis);
        inpPass = findViewById(R.id.password_regis);
        inpRePass = findViewById(R.id.retype_password);
        btnSignUp = findViewById(R.id.btn_signUp);
        txtSignIn = findViewById(R.id.btn_signIn);
        btnReset = findViewById(R.id.btn_reset);

        preferences = getSharedPreferences(name:"userInfo", mode:0);

        inpRePass.setOnKeyListener(new View.OnKeyListener() {

            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                String nameValue = inpFullname.getEditText().getText().toString();
                String emailValue = inpEmail.getEditText().getText().toString();
                String phoneValue = inpPhone.getEditText().getText().toString();
                String userValue = inpUser.getEditText().getText().toString();
                String passValue = inpPass.getEditText().getText().toString();
                String repassValue = inpRePass.getEditText().getText().toString();

                if (passValue.equals(repassValue)) {
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString(KEY_NAME, nameValue);
                    editor.putString(KEY_EMAIL, emailValue);
                    editor.putString(KEY_PHONE, phoneValue);
                    editor.putString(KEY_USER, userValue);
                    editor.putString(KEY_PASS, passValue);
                    editor.putString(KEY_REPASS, repassValue);
                    editor.apply();

                    try {
                        if (nameValue.equals("") ||
                                emailValue.equals("") ||
                                phoneValue.equals("") ||
                                userValue.equals("") ||
                                passValue.equals("") ||
                                repassValue.equals("")) {
                            Toast.makeText(context:SignUp.this, text:
                            "Data Cannot be Empty. \nData can be Exhausted.", Toast.LENGTH_LONG).show();
                        } else {
                            String name = preferences.getString(KEY_NAME, null);
                            if (name != null) {
                                if (keyEvent.getAction() == KeyEvent.ACTION_DOWN && keyCode = KeyEvent.KEYCODE_ENTER) {
                                    Toast.makeText(context:SignUp.this, text:
                                    "Successful Registration", Toast.LENGTH_LONG).show();
                                    Intent intent = new Intent(packageContext:
                                    SignUp.this, LoginPage.class);
                                    startActivity(intent);
                                    resetDetailTour();
                                    finish();
                                }
                            }
                        }
                    } catch (Exception e) {
                        Toast.makeText(context:SignUp.this, text:
                        "Username has been used", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(context:SignUp.this, text:
                    "Password doesn't match", Toast.LENGTH_LONG).show();
                }
                return false;
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameValue = inpFullname.getEditText().getText().toString();
                String emailValue = inpEmail.getEditText().getText().toString();
                String phoneValue = inpPhone.getEditText().getText().toString();
                String userValue = inpUser.getEditText().getText().toString();
                String passValue = inpPass.getEditText().getText().toString();
                String repassValue = inpRePass.getEditText().getText().toString();

                if (passValue.equals(repassValue)) {
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString(KEY_NAME, nameValue);
                    editor.putString(KEY_EMAIL, emailValue);
                    editor.putString(KEY_PHONE, phoneValue);
                    editor.putString(KEY_USER, userValue);
                    editor.putString(KEY_PASS, passValue);
                    editor.putString(KEY_REPASS, repassValue);
                    editor.apply();
                    try {
                        if (nameValue.equals("") ||
                                emailValue.equals("") ||
                                phoneValue.equals("") ||
                                userValue.equals("") ||
                                passValue.equals("") ||
                                repassValue.equals("")) {
                            Toast.makeText(context:SignUp.this, text:
                            "Data Cannot be Empty. \nData can be Exhausted.", Toast.LENGTH_LONG).
                            show();
                        } else {
                            String name = preferences.getString(KEY_NAME, null);
                            if (name != null) {
                                Toast.makeText(context:SignUp.this, text:
                                "Successful Registration", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(packageCentext:
                                SignUp.this, LoginPage.class);
                                startActivity(intent);
                                resetDetailTour();
                                finish();
                            }
                        }
                    } catch (Exception e) {
                        Toast.makeText(context:SignUp.this, text:
                        "Username has been used", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(context:SignUp.this, text:
                    "Password doesn't match", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });

        txtSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(packageContext:SignUp.this, LoginPage.class);
                startActivity(intent);
            }
        });
    }
    public void reset() {
        inpFullname.getEditText().setText(null);
        inpEmail.getEditText().setText(null);
        inpPhone.getEditText().setText(null);
        inpUser.getEditText().setText(null);
        inpPass.getEditText().setText(null);
        inpRePass.getEditText().setText(null);
    }
    private void resetDetailTour() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_NAME_TOUR, s1:null);
        editor.putString(KEY_COUNT_ITEMS, s1:null);
        editor.putString(KEY_TOTAL_PRICE, s1:null);
        editor.apply();
    }
}
