package com.example.travelbuddy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

public class HomeActivity extends AppCompatActivity {

    private ImageView btnTopPicks, btnFood, btnNature, btnShopping;
    private Button btnLocation;
    private ImageButton btnProfile;

    private double selLat = 19.0760, selLon = 72.8777; // default Mumbai

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        btnTopPicks = findViewById(R.id.btnTopPicks);
        btnFood = findViewById(R.id.btnFood);
        btnNature = findViewById(R.id.btnNature);
        btnShopping = findViewById(R.id.btnShopping);
        btnLocation = findViewById(R.id.btnLocation);
        btnProfile = findViewById(R.id.btnProfile);

        btnLocation.setOnClickListener(v -> {
            Intent i = new Intent(this, LocationSelectActivity.class);
            startActivityForResult(i, 100);
        });

        btnTopPicks.setOnClickListener(v -> openCategory("tourist"));
        btnFood.setOnClickListener(v -> openCategory("restaurants"));
        btnNature.setOnClickListener(v -> openCategory("natural"));
        btnShopping.setOnClickListener(v -> openCategory("shops"));

        btnProfile.setOnClickListener(v -> startActivity(new Intent(this, ProfileActivity.class)));
    }

    private void openCategory(String category) {
        Intent i = new Intent(this, CategoryActivity.class);
        i.putExtra("category", category);
        i.putExtra("lat", selLat);
        i.putExtra("lon", selLon);
        startActivity(i);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 100 && resultCode == RESULT_OK && data != null) {
            selLat = data.getDoubleExtra("lat", selLat);
            selLon = data.getDoubleExtra("lon", selLon);
        }
    }
}
