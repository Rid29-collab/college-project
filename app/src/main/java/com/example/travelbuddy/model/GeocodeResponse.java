package com.example.travelbuddy.model;

import com.google.gson.annotations.SerializedName;
import java.util.List;

public class GeocodeResponse {

    private List<PlaceResponse.Feature> features;

    public List<PlaceResponse.Feature> getFeatures() {
        return features;
    }
}
// This class models the response from the Geoapify Geocoding API
//public class GeocodeResponse {
//
//    @SerializedName("features")
//    private List<GeocodeFeature> features;
//
//    public List<GeocodeFeature> getFeatures() {
//        return features;
//    }
//
//    public static class GeocodeFeature {
//        @SerializedName("properties")
//        private GeocodeProperties properties;
//
//        public GeocodeProperties getProperties() {
//            return properties;
//        }
//    }
//
//    public static class GeocodeProperties {
//        @SerializedName("lat")
//        private double lat;
//
//        @SerializedName("lon")
//        private double lon;
//
//        public double getLat() {
//            return lat;
//        }
//
//        public double getLon() {
//            return lon;
//        }
//    }
//}
