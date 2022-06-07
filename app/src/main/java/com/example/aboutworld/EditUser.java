package com.example.aboutworld;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
public class EditUser extends AppCompatActivity {
    TextView txtSignIn;

    ImageView img;
    TextView txtTitle, txtSub;
    TextInputLayout inpName, inpEmail, inpPhone, inpUser, inpPass, inpRePass;
    Button btnUpdate, btnReset;

    SharedPreferences preferences;

    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_USER = "user";
    private static final String KEY_PASS = "pass";
    private static final String KEY_REPASS = "repass";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        inpName = findViewById(R.id.name_edit);
        inpEmail = findViewById(R.id.email_edit);
        inpPhone = findViewById(R.id.phone_edit);
        inpUser = findViewById(R.id.username_edit);
        inpPass = findViewById(R.id.password_edit);
        inpRePass = findViewById(R.id.retype_password_edit);

        btnUpdate = findViewById(R.id.btn_update);
        btnReset = findViewById(R.id.btn_reset);

        preferences = getSharedPreferences(name:"userInfo", mode:0);

        String nameView = preferences.getString(KEY_NAME, null);
        String emailView = preferences.getString(KEY_EMAIL, null);
        String phoneView = preferences.getString(KEY_PHONE, null);
        String userView = preferences.getString (KEY_USER, s1:null);
        String passView = preferences.getString(KEY_PASS, null);
        String repassView = preferences.getString(KEY_REPASS, s1:null);

        if (nameView != null || emailView != null || phoneView != null || userView != null || passView != null || repassView != null) {
            inpName.getEditText().setText(nameView);
            inpEmail.getEditText().setText(emailView);
            inpPhone.getEditText().setText(phoneView);
            inpUser.getEditText().setText(userView);
            inpPass.getEditText().setText(passView);
            inpRePass.getEditText().setText(repassView);
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                String nameValue = inpName.getEditText().getText().toString();
                String emailValue = inpEmail.getEditText().getText().toString();
                String phoneValue = inpPhone.getEditText().getText().toString();
                String userValue = inpUser.getEditText().getText().toString();
                String passValue = inpPass.getEditText().getText().toString();
                String repassValue = inpRePass.getEditText().getText().toString();

                if (passValue.equals(repassValue)) {
                    SharedPreferences. Editor editor = preferences.edit();
                    editor.putString(KEY_NAME, inpName.getEditText().getText().toString());
                    editor.putString(KEY_EMAIL, inpEmail.getEditText ().getText().toString());
                    editor.putString(KEY_PHONE, inpPhone.getEditText().getText().toString());
                    editor.putString(KEY_USER, inpUser.getEditText().getText().toString());
                    editor.putString(KEY_PASS, inpPass.getEditText().getText().toString());
                    editor.putString(KEY_REPASS, inpRePass.getEditText().getText().toString();
                    editor.putString(s:"Authentication_Status", s1: "true");
                    editor.apply();

                    try {
                        if (nameValue.equals("") ||
                                emailValue.equals("") ||
                                phoneValue.equals("") ||
                                userValue.equals("") ||
                                passValue.equals("") ||
                                repassValue.equals("")) {
                            Toast.makeText(context:EditUser.this, text:
                            "Data Cannot be Empty. \nData can be Exhausted.", Toast.LENGTH_LONG).
                            show();
                        }
                        else
                        {
                            String name = preferences.getString(KEY_NAME, s:null);
                            if (name != null) {
                                Toast.makeText(context:EditUser.this, text:
                                "Successful Registration", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(pakageContent:
                                EditUser.this, LoginPage.class);
                                startActivity(intent);
                                finish();
                            }
                        }
                    }catch (Exception e)
                    {
                        Toast.makeText(context: EditUser.this, text:"Username has been used", Toast.LENGTH_LONG).show();
                    }
                }
                else
                {
                    Toast.makeText(context: EditUser.this, text:"Password doesn't match", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });
    }

    public void reset() {
        inpName.getEditText().setText(null);
        inpEmail.getEditText().setText(null);
        inpPhone.getEditText().setText(null);
        inpUser.getEditText().setText(null);
        inpPass.getEditText().setText(null);
        inpRePass.getEditText().setText(null);
    }
}


