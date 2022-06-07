package com.example.aboutworld;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Dashboard extends AppCompatActivity {
    TextView txtName, txtEmail;
    Button checkTickets;

    AlertDialog alertDialog;
    MenuInflater inflater;

    private ArrayList<String> al_img_tour = new ArrayList<>();
    private ArrayList<String> al_name_tour = new ArrayList<>();
    private ArrayList<String> al_desc_tour = new ArrayList<>();
    private ArrayList<Integer> al_price_tour = new ArrayList<>();
    private ArrayList<String> al_location = new ArrayList<>();

    SharedPreferences preferences;

    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_TOTAL_PRICE = "total price";
    private static final String KEY_NAME_TOUR = "name_tour";
    private static final String KEY_COUNT_ITEMS = "count_items";

    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        txtName = findViewById(R.id.tv_fullname);
        txtEmail = findViewById(R.id.tv_email);
        checkTickets = findViewById(R.id.check_ticket);

        checkTickets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameView = preferences.getString(KEY_NAME, s1:null);
                String emailView = preferences.getString(KEY_EMAIL, s1:null);
                String phoneView = preferences.getString(KEY_PHONE, s1:null);

                String nameTourView = preferences.getString(KEY_NAME_TOUR, s1:null);
                String totalItemsView = preferences.getString(KEY_COUNT_ITEMS, s1:null);
                String totalPriceView = preferences.getString(KEY_TOTAL_PRICE, s1:null);

                if (nameView == "" || emailView == "" || phoneView == "" || nameTourView == "" || totalItemsView == "" || totalPriceView == "" || nameView == null || emailView == null || phoneView == null || nameTourView == null || totalItemsView == null || totalPriceView == null) {
                    AlertDialog dialog = new AlertDialog.Builder(context:Dashboard.this)
                            .setTitle("Check Tickets")
                            .setMessage("\nData is Empty")
                            .setIcon(android.R.drawable.ic_dialog_info)
                            .setPositiveButton(text:"OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            Intent intent = new Intent(packageContext:
                            Dashboard.this, Dashboard.class);
                            startActivity(intent);
                            finish();
                        }
                    }).show();
                } else if (nameView == nameView || emailView == emailView || phoneView == phoneView || nameTourView == nameTourView || totalItemsView == totalItemsView || totalPriceView == totalPriceView) {
                    Intent intent = new Intent(packageContext:Dashboard.this, Tickets.class);
                    startActivity(intent);
                }
            }
        });

        preferences = getSharedPreferences(name:"userInfo", mode:0);

        String nameView = preferences.getString(KEY_NAME, s1:null);
        String emailView = preferences.getString(KEY_EMAIL, s1:null);

        if (nameView != null || emailView != null) {
            txtName.setText(nameView);
            txtEmail.setText(emailView);
        }

        getData();

        toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
    }

    private void getData() {
        //Pantai Pandana
        al_img_tour.add("https://www.rentalmobilbali.net/wp-content/uploads/2016/05/Pantai-Pandana-Kutuh.jpg");
        al_name_tour.add("Pantai Pandawa");
        al_desc_tour.add("Pantai Pandawa berlokasi di Bali Selatan, tepatnya di Desa Kutuh, Kecanatan Kuta Selatan, Kabupaten Badung, Bali. Dahut");
        al_price_tour.add(8000);
        al_location.add("Pantai Pandawa, Bali");

        //Pantai Kuta
        al_img_tour.add("https://www.rentalmobilbali.net/wp-content/uploads/2016/05/Pantai-Pandana-Kutuh.jpg");
        al_name_tour.add("Pantai Pandawa");
        al_desc_tour.add("Pantai Pandawa berlokasi di Bali Selatan, tepatnya di Desa Kutuh, Kecanatan Kuta Selatan, Kabupaten Badung, Bali. Dahut");
        al_price_tour.add(8000);
        al_location.add("Pantai Pandawa, Bali");

        //Tanah Lot
        al_img_tour.add("https://www.rentalmobilbali.net/wp-content/uploads/2016/05/Pantai-Pandana-Kutuh.jpg");
        al_name_tour.add("Pantai Pandawa");
        al_desc_tour.add("Pantai Pandawa berlokasi di Bali Selatan, tepatnya di Desa Kutuh, Kecanatan Kuta Selatan, Kabupaten Badung, Bali. Dahut");
        al_price_tour.add(8000);
        al_location.add("Pantai Pandawa, Bali");
    }

    private void RecycleViewAdapterProcess() {
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        RecycleViewAdapter adapter = new RecycleViewAdapter(al_img_tour, al_name_tour, al_desc_tour, al_price_tour, al_location, context:
        this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context:this));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.bar_call_center:
                callCenter();
                return true;
            case R.id.bar_email:
                emailCenter();
                return true;
            case R.id.bar_loc:
                getLoc();
                return true;
            case R.id.bar_edit_user:
                editUser();
                return true;
            case R.id.bar_logout:
                getLogout();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void callCenter() {
        alertDialog = new AlertDialog.Builder(context:Dashboard.this)
                .setIcon(android.R.drawable.ic_dialog_dialer)
                .setTitle("Call Center")
                .setMessage("\n+918213986451")
                .setNeutralButton(text:"Call", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Uri uri = Uri.parse("+918213986451");
                Intent intent = new Intent(Intent.ACTION_DIAL, uri);
                intent.setData(Uri.fromParts(scheme:"tel", String.valueOf(uri), fragment:null));

                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                }
            }
        });
                .show();
    }

    private void emailCenter() {
        alertDialog = new AlertDialog.Builder(context:Dashboard.this)
                .setIcon(android.R.drawable.ic_dialog_email)
                .setTitle("Email")
                .setMessage("\nAboutworld@gmail.com")
                .setNeutralButton(text:"Go to Email", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"Aboutworld@gmail.com"});
                intent.putExtra(Intent.EXTRA_SUBJECT, value:"Hi, About World");
                intent.putExtra(Intent.EXTRA_TEXT, value:"ABOUT WORLD");
                intent.setType("message/rfc822");
                startActivity(Intent.createChooser(intent, title:"Choose Your Apps:"));
            }
        });
        .show();
    }

    private void getLoc() {
        alertDialog = new AlertDialog.Builder(context:Dashboard.this)
                .setIcon(android.R.drawable.ic_dialog_info)
                .setTitle("Location")
                .setMessage("\nMITS Gwalior, M.P.")
                .setNeutralButton(text:"Go to Location", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Uri uri2 = Uri.parse("geo:0,0?q=" + "MITS Gwalior, M.P.");
                Intent mapIntent = new Intent(Intent.ACTION_VIEW, uri2);
                mapIntent.setPackage("con.google.android.apps.naps");

                if (mapIntent.resolveActivity(getPackageManager()) != null) {
                    startActivity(mapIntent);
                }
            }
        });
        .show();
    }

    private void editUser() {
        Intent intent = new Intent(packageContext:Dashboard.this, EditUser.class);
        startActivity(intent);
    }

    private void getLogout() {
        Intent intent = new Intent(Dashboard.this, LoginPage.class);
        startActivity(intent);
        finish();
    }
}


