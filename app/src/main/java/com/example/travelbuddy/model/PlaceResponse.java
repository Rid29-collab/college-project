package com.example.travelbuddy.model;

import java.util.List;

public class PlaceResponse {

    private List<Properties> results;  // For Geocode API
    private List<Feature> features;    // For Places API

    public List<Properties> getResults() { return results; }
    public List<Feature> getFeatures() { return features; }

    public static class Feature {
        private Geometry geometry;
        private Properties properties;

        public Geometry getGeometry() { return geometry; }
        public Properties getProperties() { return properties; }
    }
}

//package com.example.travelbuddy.model;
//
//import java.util.List;
//
//public class PlaceResponse {
//
//    private String type;
//    private List<Feature> features;
//
//    public String getType() {
//        return type;
//    }
//
//    public List<Feature> getFeatures() {
//        return features;
//    }
//
//    public static class Feature {
//        private Geometry geometry;
//        private Properties properties;
//
//        public Geometry getGeometry() {
//            return geometry;
//        }
//
//        public Properties getProperties() {
//            return properties;
//        }
//    }
//}
