package com.example.travelbuddy.model;

public class Properties {

    private String name;
    private String formatted;
    // Geoapify sends an array of category strings.
    private java.util.List<String> categories;
    private double lat;
    private double lon;

    public String getName() { return name; }
    public String getFormatted() { return formatted; }
    public java.util.List<String> getCategories() { return categories; }
    public double getLat() { return lat; }
    public double getLon() { return lon; }
}


//package com.example.travelbuddy.model;
//
//public class Properties {
//
//    private String place_id;
//    private String name;
//    private String street;
//    private String housenumber;
//    private String postcode;
//    private String city;
//    private String state;
//    private String country;
//    private String formatted;
//    private String categories;
//
//    private double lat;  // Add latitude
//    private double lon;  // Add longitude
//
//    // Getters and setters
//    public String getPlaceId() { return place_id; }
//    public void setPlaceId(String place_id) { this.place_id = place_id; }
//
//    public String getName() { return name != null ? name : "Unknown"; }
//    public void setName(String name) { this.name = name; }
//
//    public String getStreet() { return street; }
//    public void setStreet(String street) { this.street = street; }
//
//    public String getHousenumber() { return housenumber; }
//    public void setHousenumber(String housenumber) { this.housenumber = housenumber; }
//
//    public String getPostcode() { return postcode; }
//    public void setPostcode(String postcode) { this.postcode = postcode; }
//
//    public String getCity() { return city; }
//    public void setCity(String city) { this.city = city; }
//
//    public String getState() { return state; }
//    public void setState(String state) { this.state = state; }
//
//    public String getCountry() { return country; }
//    public void setCountry(String country) { this.country = country; }
//
//    public String getFormatted() { return formatted; }
//    public void setFormatted(String formatted) { this.formatted = formatted; }
//
//    public String getCategories() { return categories; }
//    public void setCategories(String categories) { this.categories = categories; }
//
//    public double getLat() { return lat; }       // Getter for latitude
//    public void setLat(double lat) { this.lat = lat; }
//
//    public double getLon() { return lon; }       // Getter for longitude
//    public void setLon(double lon) { this.lon = lon; }
//}
