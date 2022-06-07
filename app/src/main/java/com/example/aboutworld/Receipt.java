package com.example.aboutworld;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class Receipt extends AppCompatActivity {
    ImageView imgTour;
    TextView nameTour, totalPeople, priceTour, totalPrice, name, email, phone;
    Button btnConfirm;
    AlertDialog dialog;
    SharedPreferences preferences;
    String CHANNEL_ID = "About World App V1.0 (BETA)";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PHONE = "phone";

    private static final String KEY_IMG_TOUR = "img_tour";
    private static final String KEY_TOTAL_PRICE = "total_price";
    private static final String KEY_NAME_TOUR = "name_tour";
    private static final String KEY_COUNT_ITEMS = "count_items";
    private static final String KEY_PRICE_TOUR = "price_tour";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_receipt);

        imgTour = findViewById(R.id.img_tour);
        nameTour = findViewById(R.id.name_tour);
        totalPeople = findViewById(R.id.total_people);
        priceTour = findViewById(R.id.price_tour);
        totalPrice = findViewById(R.id.total_price);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        phone = findViewById(R.id.phone);
        btnConfirm = findViewById(R.id.btn_confirm);

        preferences = getSharedPreferences(name:"userInfo", mode:0);

        String nameView = preferences.getString(KEY_NAME, s1:null);
        String emailView = preferences.getString(KEY_EMAIL, s1:null);
        String phoneView = preferences.getString(KEY_PHONE, s1:null);

        String imgTourView = preferences.getString(KEY_IMG_TOUR, s1:null);
        String nameTourView = preferences.getString(KEY_NAME_TOUR, s1:null);
        String totalItemsView = preferences.getString(KEY_COUNT_ITEMS, null);
        String priceView = preferences.getString(KEY_PRICE_TOUR, s1:null);
        String totalPriceView = preferences.getString(KEY_TOTAL_PRICE, s1:null);

        if (nameView != null || emailView != null || phoneView != null || imgTourView != null || nameTourView != null || totalPriceView != null || totalItemView != null || priceView != null) {
            Glide.with(activity:this).asBitmap().load(imgTourView).into(imgTour)
            name.setText(nameView);
            email.setText(emailView);
            phone.setText(phoneView);
            nameTour.setText(nameTourView);
            priceTour.setText("Rp" + priceView);
            totalPeople.setText(totalItemsView + "Orang");
            totalPrice.setText("Rp" + totalPriceView);
        }

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog = new AlertDialog.Builder(context:Receipt.this)
                        .setIcon(android.R.drawable.ic_dialog_info)
                        .setTitle("Message")
                        .setHessage("\nÂre you sure booked this spot?")
                        .setPositiveButton(text:"Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int 1) {
                        Toast.makeText(context:Receipt.this, text:
                        "Success Booked Ticket", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(packageContext:
                        Receipt.this, Receipt.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        PendingIntent pendingIntent = PendingIntent.getActivity(context:
                        Receipt.this, requestCode:0, intent, flags:0);

                        NotificationCompat.Builder builder = new NotificationCompat.Builder(context:
                        Receipt.this, CHANNEL_ID)
                                .setSmallIcon(R.drawable.ic_ticket)
                                .setContentTitle("Ticket Details")
                                .setStyle(new NotificationCompat.BigTextStyle()
                                        .bigText("\nYour Ticket Successfully Booked!\n" +
                                                "=======================================" + "\n" +
                                                "Name Passenger\t: " + nameView + "\n" +
                                                "Name Tour\t: " + nameTourView + "\n" +
                                                "Total Person\t: " + totalItemsView + "\n" +
                                                "Total Price\t: Rp" + totalPriceView + "\n" +
                                                "======================================="))
                                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                                // Set the intent that will fire when the user taps the notification
                                .setContentIntent(pendingIntent)
                                .setAutoCancel(true);
                        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(Receipt.this);
                        notificationManager.notify(id:25, builder.build());
                        finish();
                    }
                })
                .setNegativeButton(text:"No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        resetDetailTour();
                        Toast.makeText(context:Receipt.this, text
                        "Fail Booked Ticket", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(Receipt.this, Dashboard.class);
                        startActivity(intent);
                        finish();
                    }
                });
                .show();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChannel() {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is nem and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.0){
            CharSequence name = "About World App V1.0 (BETA)";
            String description = "About World Travel India";
            int importance = NotificationManager.IMPORTANCE_DEFAULT;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.setDescription(description);
            // Register the channel with the system; you can't change the importance
            // or other notification behaviors after this
            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }

    private void resetDetailTour() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_NAME_TOUR, s1:null);
        editor.putString(KEY_COUNT_ITEMS, s1:null);
        editor.putString(KEY_TOTAL_PRICE, s1:null);
        editor.apply();
    }
}