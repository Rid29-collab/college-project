package com.example.travelbuddy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.osmdroid.config.Configuration;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapView;
import org.osmdroid.views.overlay.Marker;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;

import com.example.travelbuddy.api.GeoapifyClient;
import com.example.travelbuddy.model.GeocodeResponse;
import com.example.travelbuddy.model.PlaceResponse;

public class LocationSelectActivity extends AppCompatActivity {

    private static final int REQ_PERM = 123;

    private MapView map;
    private EditText etSearch;
    private Button btnSearch, btnSelect;
    private Marker marker;

    // Default location → Mumbai
    private double selLat = 19.0760;
    private double selLon = 72.8777;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Configuration.getInstance().setUserAgentValue(getPackageName());
        setContentView(R.layout.activity_location_select);

        etSearch = findViewById(R.id.searchBox);
        btnSearch = findViewById(R.id.btnSearch);
        btnSelect = findViewById(R.id.btnSelectLocation);
        map = findViewById(R.id.mapSelect);

        initMap();
        checkPermissions();

        btnSearch.setOnClickListener(v -> searchLocation());
        btnSelect.setOnClickListener(v -> returnSelectedLocation());
    }

    private void initMap() {
        map.setTileSource(TileSourceFactory.MAPNIK);
        map.getController().setZoom(12);

        GeoPoint startPoint = new GeoPoint(selLat, selLon);
        map.getController().setCenter(startPoint);

        marker = new Marker(map);
        marker.setPosition(startPoint);
        marker.setDraggable(true);
        marker.setTitle("Selected Location");

        map.getOverlays().add(marker);
    }

    private void searchLocation() {
        String query = etSearch.getText().toString().trim();

        if (TextUtils.isEmpty(query)) {
            Toast.makeText(this, "Enter city name", Toast.LENGTH_SHORT).show();
            return;
        }

        new Thread(() -> {
            GeocodeResponse response = GeoapifyClient.geocode(query);

            if (response == null || response.getFeatures() == null || response.getFeatures().isEmpty()) {
                runOnUiThread(() ->
                        Toast.makeText(this, "City not found", Toast.LENGTH_SHORT).show());
                return;
            }

            PlaceResponse.Feature feature = response.getFeatures().get(0);
            double lat = feature.getProperties().getLat();
            double lon = feature.getProperties().getLon();

            selLat = lat;
            selLon = lon;

            runOnUiThread(() -> {
                GeoPoint point = new GeoPoint(selLat, selLon);
                marker.setPosition(point);
                map.getController().setCenter(point);
                map.invalidate();
            });

        }).start();
    }

    private void returnSelectedLocation() {
        GeoPoint selectedPoint = marker.getPosition();

        Intent i = new Intent();
        i.putExtra("lat", selectedPoint.getLatitude());
        i.putExtra("lon", selectedPoint.getLongitude());

        setResult(RESULT_OK, i);
        finish();
    }

    private void checkPermissions() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(
                    this,
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    REQ_PERM
            );
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        map.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        map.onPause();
    }
}




//package com.example.travelbuddy;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.app.ActivityCompat;
//
//import android.Manifest;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import org.osmdroid.config.Configuration;
//import org.osmdroid.util.GeoPoint;
//import org.osmdroid.views.MapView;
//import org.osmdroid.views.overlay.Marker;
//import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
//
//import com.example.travelbuddy.api.GeoapifyClient;
//import com.example.travelbuddy.model.PlaceResponse;
//import com.example.travelbuddy.model.Properties;
//
//public class LocationSelectActivity extends AppCompatActivity {
//
//    private static final int REQ_PERM = 100;
//    private MapView map;
//    private EditText etSearch;
//    private Button btnSearch, btnSelect;
//    private Marker marker;
//
//    // Default coordinates (Mumbai)
//    private double selLat = 19.0760, selLon = 72.8777;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        Configuration.getInstance().setUserAgentValue(getPackageName());
//        setContentView(R.layout.activity_location_select);
//
//        etSearch = findViewById(R.id.searchBox);
//        btnSearch = findViewById(R.id.btnSearch);
//        btnSelect = findViewById(R.id.btnSelectLocation);
//        map = findViewById(R.id.mapSelect);
//
//        initMap();
//        checkPermissions();
//
//        btnSearch.setOnClickListener(v -> doSearch());
//        btnSelect.setOnClickListener(v -> returnLocation());
//    }
//
//    private void initMap() {
//        map.setTileSource(TileSourceFactory.MAPNIK);
//        map.getController().setZoom(12);
//        GeoPoint start = new GeoPoint(selLat, selLon);
//        map.getController().setCenter(start);
//
//        marker = new Marker(map);
//        marker.setPosition(start);
//        marker.setDraggable(true);
//
//        map.getOverlays().add(marker);
//    }
//
//    private void doSearch() {
//        String q = etSearch.getText().toString().trim();
//
//        if (TextUtils.isEmpty(q)) {
//            Toast.makeText(this, "Enter a city name", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        new Thread(() -> {
//            PlaceResponse response = GeoapifyClient.geocode(q);
//
//            if (response == null || response.getResults() == null || response.getResults().isEmpty()) {
//                runOnUiThread(() -> Toast.makeText(this, "City not found", Toast.LENGTH_SHORT).show());
//                return;
//            }
//
//            Properties p = response.getResults().get(0);
//
//            selLat = p.getLat();
//            selLon = p.getLon();
//
//            runOnUiThread(() -> {
//                GeoPoint point = new GeoPoint(selLat, selLon);
//                marker.setPosition(point);
//                map.getController().setCenter(point);
//            });
//
//        }).start();
//    }
//
//    private void returnLocation() {
//        GeoPoint p = marker.getPosition();
//        Intent i = new Intent();
//        i.putExtra("lat", p.getLatitude());
//        i.putExtra("lon", p.getLongitude());
//        setResult(RESULT_OK, i);
//        finish();
//    }
//
//    private void checkPermissions() {
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
//                != PackageManager.PERMISSION_GRANTED) {
//
//            ActivityCompat.requestPermissions(
//                    this,
//                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
//                    REQ_PERM
//            );
//        }
//    }
//}

//package com.example.travelbuddy;
//
//import androidx.appcompat.app.AppCompatActivity;
//import androidx.core.app.ActivityCompat;
//
//import android.Manifest;
//import android.content.Intent;
//import android.content.pm.PackageManager;
//import android.os.Bundle;
//import android.text.TextUtils;
//import android.widget.Button;
//import android.widget.EditText;
//import android.widget.Toast;
//
//import org.osmdroid.config.Configuration;
//import org.osmdroid.util.GeoPoint;
//import org.osmdroid.views.MapView;
//import org.osmdroid.views.overlay.Marker;
//import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
//
//import com.example.travelbuddy.api.GeoapifyClient;
//import com.example.travelbuddy.model.PlaceResponse;
//import com.example.travelbuddy.model.Properties;
//
//public class LocationSelectActivity extends AppCompatActivity {
//
//    private static final int REQ_PERM = 123;
//    private MapView map;
//    private EditText etSearch;
//    private Button btnSearch, btnSelect;
//    private Marker marker;
//    private double selLat = 19.0760, selLon = 72.8777; // Default Mumbai
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        Configuration.getInstance().setUserAgentValue(getPackageName());
//        setContentView(R.layout.activity_location_select);
//
//        etSearch = findViewById(R.id.searchBox);
//        btnSearch = findViewById(R.id.btnSearch);
//        btnSelect = findViewById(R.id.btnSelectLocation);
//        map = findViewById(R.id.mapSelect);
//
//        initMap();
//        checkPermissions();
//
//        btnSearch.setOnClickListener(v -> searchLocation());
//        btnSelect.setOnClickListener(v -> returnSelectedLocation());
//    }
//
//    private void initMap() {
//        map.setTileSource(TileSourceFactory.MAPNIK);
//        map.getController().setZoom(12);
//        GeoPoint startPoint = new GeoPoint(selLat, selLon);
//        map.getController().setCenter(startPoint);
//
//        marker = new Marker(map);
//        marker.setPosition(startPoint);
//        marker.setDraggable(true);
//        marker.setTitle("Selected Location");
//        map.getOverlays().add(marker);
//    }
//
//    private void searchLocation() {
//        String query = etSearch.getText().toString().trim();
//        if (TextUtils.isEmpty(query)) {
//            Toast.makeText(this, "Enter city name", Toast.LENGTH_SHORT).show();
//            return;
//        }
//
//        new Thread(() -> {
//            PlaceResponse response = GeoapifyClient.geocode(query);
//            if (response != null && response.getFeatures() != null && !response.getFeatures().isEmpty()) {
//                Properties prop = response.getFeatures().get(0).getProperties();
//                selLat = prop.getLat();
//                selLon = prop.getLon();
//
//                runOnUiThread(() -> {
//                    GeoPoint point = new GeoPoint(selLat, selLon);
//                    marker.setPosition(point);
//                    map.getController().setCenter(point);
//                    map.invalidate();
//                });
//            } else {
//                runOnUiThread(() -> Toast.makeText(this, "City not found", Toast.LENGTH_SHORT).show());
//            }
//        }).start();
//    }
//
//    private void returnSelectedLocation() {
//        GeoPoint point = marker.getPosition();
//        Intent intent = new Intent();
//        intent.putExtra("lat", point.getLatitude());
//        intent.putExtra("lon", point.getLongitude());
//        setResult(RESULT_OK, intent);
//        finish();
//    }
//
//    private void checkPermissions() {
//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
//                != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this,
//                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, REQ_PERM);
//        }
//    }
//
//    @Override
//    protected void onResume() {
//        super.onResume();
//        map.onResume();
//    }
//
//    @Override
//    protected void onPause() {
//        super.onPause();
//        map.onPause();
//    }
//}
