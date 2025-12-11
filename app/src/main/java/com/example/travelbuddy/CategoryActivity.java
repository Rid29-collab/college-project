package com.example.travelbuddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import com.example.travelbuddy.adapters.PlacesAdapter;
import com.example.travelbuddy.api.GeoapifyClient;
import com.example.travelbuddy.model.Place;
import com.example.travelbuddy.model.PlaceResponse;

import java.util.ArrayList;
import java.util.List;

public class CategoryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private PlacesAdapter adapter;
    private List<Place> places = new ArrayList<>();
    private String category;
    private double lat, lon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);

        recyclerView = findViewById(R.id.placesRv);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        adapter = new PlacesAdapter(places, p ->
                Toast.makeText(this, p.getName(), Toast.LENGTH_SHORT).show()
        );

        recyclerView.setAdapter(adapter);

        category = getIntent().getStringExtra("category");
        lat = getIntent().getDoubleExtra("lat", 19.0760);
        lon = getIntent().getDoubleExtra("lon", 72.8777);

        category = mapCategory(category);
        fetchPlaces();
    }

    private String mapCategory(String cat) {
        switch (cat) {
            case "toppicks": return "entertainment";
            case "restaurants": return "catering.restaurant";
            case "natural": return "natural";
            case "shops": return "commercial.shopping";
            default: return "entertainment";
        }
    }

    private void fetchPlaces() {

        new Thread(() -> {
            PlaceResponse response = GeoapifyClient.searchNearby(lat, lon, category);

            if (response == null || response.getFeatures() == null)
            {
                runOnUiThread(() ->
                        Toast.makeText(this, "No places found", Toast.LENGTH_SHORT).show());
                return;
            }

            List<Place> parsed = new ArrayList<>();

            for (PlaceResponse.Feature f : response.getFeatures()) {

                Place p = new Place();
                p.setName(f.getProperties().getName());
                p.setFormatted(f.getProperties().getFormatted());
                p.setCategories(f.getProperties().getCategories());
                p.setLat(f.getGeometry().getLat());
                p.setLon(f.getGeometry().getLon());

                parsed.add(p);
            }

            runOnUiThread(() -> {
                places.clear();
                places.addAll(parsed);
                adapter.notifyDataSetChanged();
            });
        }).start();
    }
}





//package com.example.travelbuddy;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.os.Bundle;
//import android.widget.Toast;
//
//import com.example.travelbuddy.adapters.PlacesAdapter;
//import com.example.travelbuddy.api.GeoapifyClient;
//import com.example.travelbuddy.model.Place;
//import com.example.travelbuddy.model.PlaceResponse;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class CategoryActivity extends AppCompatActivity {
//
//    private RecyclerView recyclerView;
//    private PlacesAdapter adapter;
//    private List<Place> places = new ArrayList<>();
//    private String category;
//    private double lat, lon;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_category);
//
//        recyclerView = findViewById(R.id.placesRv);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//
//        adapter = new PlacesAdapter(places, place -> {
//            Toast.makeText(this,
//                    "Selected: " + place.getName() +
//                            "\nLat: " + place.getLat() +
//                            "\nLon: " + place.getLon(),
//                    Toast.LENGTH_SHORT).show();
//        });
//
//        recyclerView.setAdapter(adapter);
//
//        category = getIntent().getStringExtra("category");
//        lat = getIntent().getDoubleExtra("lat", 19.0760);
//        lon = getIntent().getDoubleExtra("lon", 72.8777);
//
//        category = mapCategory(category);
//        fetchPlaces();
//    }
//
//    private String mapCategory(String homeCategory) {
//
//        switch (homeCategory.toLowerCase()) {
//
//            case "toppicks":
//                return "tourism.attraction";
//
//            case "restaurants":
//                return "catering.restaurant";
//
//            case "natural":
//                return "entertainment.park";
//
//            case "shopping":
//            case "shops":
//                return "commercial.shopping_mall";
//
//            default:
//                return "tourism.attraction";
//        }
//    }
//
//    private void fetchPlaces() {
//
//        Toast.makeText(this, "Loading places...", Toast.LENGTH_SHORT).show();
//
//        new Thread(() -> {
//
//            PlaceResponse response = GeoapifyClient.searchNearby(lat, lon, category);
//
//            if (response == null || response.getFeatures() == null) {
//
//                runOnUiThread(() ->
//                        Toast.makeText(this, "No places found", Toast.LENGTH_SHORT).show()
//                );
//                return;
//            }
//
//            List<Place> parsedPlaces = new ArrayList<>();
//
//            for (PlaceResponse.Feature feature : response.getFeatures()) {
//
//                Place place = new Place();
//
//                if (feature.getProperties() != null) {
//                    place.setName(feature.getProperties().getName());
//                    place.setFormatted(feature.getProperties().getFormatted());
//                    place.setCategories(feature.getProperties().getCategories());
//                }
//
//                if (feature.getGeometry() != null) {
//                    place.setLat(feature.getGeometry().getLat());
//                    place.setLon(feature.getGeometry().getLon());
//                }
//
//                parsedPlaces.add(place);
//            }
//
//            runOnUiThread(() -> {
//                places.clear();
//                places.addAll(parsedPlaces);
//                adapter.notifyDataSetChanged();
//            });
//
//        }).start();
//    }
//}

//package com.example.travelbuddy;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.recyclerview.widget.LinearLayoutManager;
//import androidx.recyclerview.widget.RecyclerView;
//
//import android.os.Bundle;
//import android.widget.Toast;
//
//import com.example.travelbuddy.adapters.PlacesAdapter;
//import com.example.travelbuddy.api.GeoapifyClient;
//import com.example.travelbuddy.model.Place;
//import com.example.travelbuddy.model.PlaceResponse;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class CategoryActivity extends AppCompatActivity {
//
//    private RecyclerView recyclerView;
//    private PlacesAdapter adapter;
//    private List<Place> places = new ArrayList<>();
//    private String category;
//    private double lat, lon;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_category);
//
//        recyclerView = findViewById(R.id.placesRv);
//        recyclerView.setLayoutManager(new LinearLayoutManager(this));
//        adapter = new PlacesAdapter(places, place -> {
//            Toast.makeText(this, "Selected: " + place.getName() + "\nLat: " + place.getLat() + ", Lon: " + place.getLon(), Toast.LENGTH_SHORT).show();
//        });
//        recyclerView.setAdapter(adapter);
//
//        category = getIntent().getStringExtra("category");
//        lat = getIntent().getDoubleExtra("lat", 19.0760);
//        lon = getIntent().getDoubleExtra("lon", 72.8777);
//
//        category = mapCategory(category); // Map to Geoapify category
//        fetchPlaces();
//    }
//
//    private String mapCategory(String homeCategory) {
//        switch (homeCategory.toLowerCase()) {
//            case "toppicks": return "entertainment";
//            case "restaurants": return "catering.restaurant";
//            case "natural": return "natural";
//            case "shops": return "commercial.shopping";
//            default: return "entertainment";
//        }
//    }
//
//    private void fetchPlaces() {
//        Toast.makeText(this, "Loading places...", Toast.LENGTH_SHORT).show();
//
//        new Thread(() -> {
//            PlaceResponse response = GeoapifyClient.searchNearby(lat, lon, category);
//
//            if (response == null || response.getFeatures() == null || response.getFeatures().isEmpty()) {
//                runOnUiThread(() -> Toast.makeText(this, "No places found", Toast.LENGTH_SHORT).show());
//                return;
//            }
//
//            List<Place> parsedPlaces = new ArrayList<>();
//            for (PlaceResponse.Feature feature : response.getFeatures()) {
//                Place place = new Place();
//                if (feature.getProperties() != null) {
//                    place.setName(feature.getProperties().getName());
//                    place.setCategories(feature.getProperties().getCategories());
//                }
//                if (feature.getGeometry() != null) {
//                    place.setLat(feature.getGeometry().getLat());
//                    place.setLon(feature.getGeometry().getLon());
//                }
//                parsedPlaces.add(place);
//            }
//
//            runOnUiThread(() -> {
//                places.clear();
//                places.addAll(parsedPlaces);
//                adapter.notifyDataSetChanged();
//            });
//
//        }).start();
//    }
//}
