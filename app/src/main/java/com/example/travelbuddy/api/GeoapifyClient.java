package com.example.travelbuddy.api;

import android.util.Log;

import com.example.travelbuddy.model.GeocodeResponse;
import com.example.travelbuddy.model.PlaceResponse;
import com.google.gson.Gson;

import java.util.Locale;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GeoapifyClient {

    private static final String API_KEY = "96da8c77dd46420ea8bb62a5447b5891";

    private static final String GEOCODE_ENDPOINT = "https://api.geoapify.com/v1/geocode/search";
    private static final String PLACES_ENDPOINT = "https://api.geoapify.com/v2/places";

    // Shared OkHttp client with sensible timeouts for mobile networks.
    private static final OkHttpClient client = new OkHttpClient.Builder()
            .connectTimeout(15, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .build();
    private static final Gson gson = new Gson();


    // ------------- GEOCODING (CITY SEARCH) --------------------

    public static GeocodeResponse geocode(String query) {
        HttpUrl url = HttpUrl.parse(GEOCODE_ENDPOINT)
                .newBuilder()
                .addQueryParameter("text", query)
                .addQueryParameter("limit", "1")
                .addQueryParameter("apiKey", API_KEY)
                .build();

        Request req = new Request.Builder().url(url).build();

        try (Response response = client.newCall(req).execute()) {

            if (!response.isSuccessful() || response.body() == null) {
                Log.e("Geoapify", "Geocode error: " + response.code() + " body=" + (response.body() != null ? response.body().string() : "null"));
                return null;
            }

            return gson.fromJson(response.body().string(), GeocodeResponse.class);

        } catch (Exception e) {
            Log.e("Geoapify", "Geocode exception", e);
            return null;
        }
    }


    // ------------- PLACES API (CATEGORY SEARCH) --------------------

    public static PlaceResponse searchNearby(double lat, double lon, String category) {

        String filter = String.format(Locale.US, "circle:%f,%f,10000", lon, lat);

        HttpUrl url = HttpUrl.parse(PLACES_ENDPOINT)
                .newBuilder()
                .addQueryParameter("categories", category)
                .addQueryParameter("filter", filter)
                .addQueryParameter("limit", "20")
                .addQueryParameter("apiKey", API_KEY)
                .build();

        Request req = new Request.Builder().url(url).build();

        try (Response response = client.newCall(req).execute()) {

            if (!response.isSuccessful() || response.body() == null) {
                Log.e("Geoapify", "Places error: " + response.code() + " body=" + (response.body() != null ? response.body().string() : "null"));
                return null;
            }

            return gson.fromJson(response.body().string(), PlaceResponse.class);

        } catch (Exception e) {
            Log.e("Geoapify", "Places exception", e);
            return null;
        }
    }
}





//package com.example.travelbuddy.api;
//
//import android.util.Log;
//
//// Import the new GeocodeResponse model you will create
//import com.example.travelbuddy.model.GeocodeResponse;
//import com.example.travelbuddy.model.PlaceResponse;
//import com.google.gson.Gson;
//
//import java.io.IOException;
//import java.net.URLEncoder;
//
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//
//public class GeoapifyClient {
//
//    private static final String API_KEY = "96da8c77dd46420ea8bb62a5447b5891";
//    private static final String BASE_URL = "https://api.geoapify.com/v2/places";
//
//    private static OkHttpClient client = new OkHttpClient();
//    private static Gson gson = new Gson();
//
//    // This method is correct and remains unchanged.
//    // It searches for places with categories.
//    public static PlaceResponse searchNearby(double lat, double lon, String category) {
//        try {
//            String url = BASE_URL
//                    + "?categories=" + URLEncoder.encode(category, "UTF-8")
//                    + "&filter=circle:" + lon + "," + lat + ",15000" // 15 km radius
//                    + "&limit=20"
//                    + "&apiKey=" + API_KEY;
//
//            Request request = new Request.Builder().url(url).build();
//            Response response = client.newCall(request).execute();
//
//            if (!response.isSuccessful()) {
//                Log.e("GeoapifyClient", "API Error: " + response.code() + " " + response.message());
//                return null;
//            }
//
//            String json = response.body().string();
//            return gson.fromJson(json, PlaceResponse.class);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    // --- THIS METHOD HAS BEEN UPDATED ---
//    // It now correctly returns a GeocodeResponse object, not a PlaceResponse.
//    public static GeocodeResponse geocode(String cityName) {
//        try {
//            String url = "https://api.geoapify.com/v1/geocode/search"
//                    + "?text=" + URLEncoder.encode(cityName, "UTF-8")
//                    + "&limit=1"
//                    + "&apiKey=" + API_KEY;
//
//            Request request = new Request.Builder().url(url).build();
//            Response response = client.newCall(request).execute();
//
//            if (!response.isSuccessful()) {
//                Log.e("GeoapifyClient", "Geocode API Error: " + response.code() + " " + response.message());
//                return null;
//            }
//
//            String json = response.body().string();
//            // Use the new GeocodeResponse class to parse the result
//            return gson.fromJson(json, GeocodeResponse.class);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//}




//package com.example.travelbuddy.api;
//
//import android.util.Log;
//
//import com.example.travelbuddy.model.PlaceResponse;
//import com.google.gson.Gson;
//
//import java.io.IOException;
//import java.net.URLEncoder;
//
//import okhttp3.OkHttpClient;
//import okhttp3.Request;
//import okhttp3.Response;
//
//public class GeoapifyClient {
//
//    private static final String API_KEY = "96da8c77dd46420ea8bb62a5447b5891";
//    private static final String BASE_URL = "https://api.geoapify.com/v2/places";
//
//    private static OkHttpClient client = new OkHttpClient();
//    private static Gson gson = new Gson();
//
//    // Search nearby places by category
//    public static PlaceResponse searchNearby(double lat, double lon, String category) {
//        try {
//            String url = BASE_URL
//                    + "?categories=" + URLEncoder.encode(category, "UTF-8")
//                    + "&filter=circle:" + lon + "," + lat + ",15000" // 15 km radius
//                    + "&limit=20"
//                    + "&apiKey=" + API_KEY;
//
//            Request request = new Request.Builder().url(url).build();
//
//            Response response = client.newCall(request).execute();
//
//            if (!response.isSuccessful()) {
//                Log.e("GeoapifyClient", "API Error: " + response.code());
//                return null;
//            }
//
//            String json = response.body().string();
//            return gson.fromJson(json, PlaceResponse.class);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//
//    // Geocode city name to coordinates
//    public static PlaceResponse geocode(String cityName) {
//        try {
//            String url = "https://api.geoapify.com/v1/geocode/search"
//                    + "?text=" + URLEncoder.encode(cityName, "UTF-8")
//                    + "&limit=1"
//                    + "&apiKey=" + API_KEY;
//
//            Request request = new Request.Builder().url(url).build();
//            Response response = client.newCall(request).execute();
//
//            if (!response.isSuccessful()) {
//                Log.e("GeoapifyClient", "Geocode API Error: " + response.code());
//                return null;
//            }
//
//            String json = response.body().string();
//            return gson.fromJson(json, PlaceResponse.class);
//
//        } catch (IOException e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
//}
