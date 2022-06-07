package com.example.aboutworld;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

public class Tickets extends AppCompatActivity {
    TextView name, email, phone, nameTour, totalPeople, totalPrice;
    Button btnBack;
    AlertDialog dialog;
    SharedPreferences preferences;
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email"
    private static final String KEY_PHONE = "phone";
    private static final String KEY_TOTAL_PRICE = "total_price";
    private static final String KEY_NAME_TOUR = "name_tour";
    private static final String KEY_COUNT_ITEMS = "count_items";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tickets);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        nameTour = findViewById(R.id.name_tour);
        totalPeople = findViewById(R.id.total_people);
        totalPrice = findViewById(R.id.total_price);
        btnBack = findViewById(R.id.btn_back);

        preferences getSharedPreferences (name:"userInfo", mode:0);

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(packageContext:Tickets.this, Dashboard.class);
                startActivity(intent);
                finish();
            }
        });

        String nameView = preferences.getString(KEY_NAME, s1:null);
        String emailView = preferences.getString(KEY_EMAIL, s1:null);
        String phoneView = preferences.getString(KEY_PHONE, sl:null);

        String nameTourView = preferences.getString(KEY_NAME_TOUR, s1:null);
        String totalItemsView = preferences.getString(KEY_COUNT_ITEMS, null);
        String totalPriceView = preferences.getString(KEY_TOTAL_PRICE, s1:null);

        if (nameView != null || emailView != null || phoneView != null || nameTourView != null || totalItemsView != null || totalPriceView != null) {
            name.setText(nameView);
            email.setText(emailView);
            phone.setText(phoneView);
            nameTour.setText(nameTourView);
            totalPeople.setText(totalItemsView + "Orang");
            totalPrice.setText("Rp" + totalPriceView);
        } else {
            dialog = new AlertDialog.Builder(context:Tickets.this)
                    .setTitle("Check Tickets")
                    .setMessage("Data is Empty")
                    .setIcon(android.R.drawable.ic_dialog_info)
                    .setPositiveButton(text:"OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent(packageContext:Tickets.this, Dashboard.class)
                    ;
                    startActivity(intent);
                    finish();
                }
            }).show();
        }
    }
}
