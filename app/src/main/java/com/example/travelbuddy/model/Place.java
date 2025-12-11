package com.example.travelbuddy.model;

public class Place {

    private String name;
    private String formatted;
    private String categories;
    private double lat;
    private double lon;

    // Setters
    public void setName(String n) { name = n; }
    public void setFormatted(String f) { formatted = f; }
    public void setCategories(String c) { categories = c; }
    public void setLat(double l) { lat = l; }
    public void setLon(double l) { lon = l; }

    // Getters
    public String getName() { return name; }
    public String getFormatted() { return formatted; }
    public String getCategories() { return categories; }
    public double getLat() { return lat; }
    public double getLon() { return lon; }
}




//package com.example.travelbuddy.model;
//
//public class Place {
//
//    private String name;
//    private String formatted;
//    private String categories;
//
//    private double lat;
//    private double lon;
//
//    public String getName() { return name != null ? name : "Unknown Place"; }
//    public void setName(String name) { this.name = name; }
//
//    public String getFormatted() { return formatted != null ? formatted : ""; }
//    public void setFormatted(String formatted) { this.formatted = formatted; }
//
//    public String getCategories() { return categories != null ? categories : "Unknown"; }
//    public void setCategories(String categories) { this.categories = categories; }
//
//    public double getLat() { return lat; }
//    public void setLat(double lat) { this.lat = lat; }
//
//    public double getLon() { return lon; }
//    public void setLon(double lon) { this.lon = lon; }
//}

//package com.example.travelbuddy.model;
//
//import java.util.Map;
//import java.util.HashMap;
//
//public class Place {
//
//    private String name;
//    private String categories;
//
//    private double lat;
//    private double lon;
//
//    public String getName() {
//        return name != null ? name : "Unknown Place";
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public String getCategories() {
//        return categories != null ? categories : "Unknown Category";
//    }
//
//    public void setCategories(String categories) {
//        this.categories = categories;
//    }
//
//    public double getLat() {
//        return lat;
//    }
//
//    public void setLat(double lat) {
//        this.lat = lat;
//    }
//
//    public double getLon() {
//        return lon;
//    }
//
//    public void setLon(double lon) {
//        this.lon = lon;
//    }
//}
//
////package com.example.travelbuddy.model;
////
////public class Place {
////
////    private String place_id;
////    private String name;
////    private String street;
////    private String city;
////    private String state;
////    private String country;
////    private String formatted;
////    private String categories;
////
////    private double lat; // NEW
////    private double lon; // NEW
////
////    // Getters & setters
////
////    public String getPlaceId() { return place_id; }
////    public void setPlaceId(String place_id) { this.place_id = place_id; }
////
////    public String getName() { return name != null ? name : "Unknown"; }
////    public void setName(String name) { this.name = name; }
////
////    public String getStreet() { return street; }
////    public void setStreet(String street) { this.street = street; }
////
////    public String getCity() { return city; }
////    public void setCity(String city) { this.city = city; }
////
////    public String getState() { return state; }
////    public void setState(String state) { this.state = state; }
////
////    public String getCountry() { return country; }
////    public void setCountry(String country) { this.country = country; }
////
////    public String getFormatted() { return formatted; }
////    public void setFormatted(String formatted) { this.formatted = formatted; }
////
////    public String getCategories() { return categories != null ? categories : "Unknown"; }
////    public void setCategories(String categories) { this.categories = categories; }
////
////    // NEW: lat/lon getter & setters
////    public double getLat() { return lat; }
////    public void setLat(double lat) { this.lat = lat; }
////
////    public double getLon() { return lon; }
////    public void setLon(double lon) { this.lon = lon; }
////}
