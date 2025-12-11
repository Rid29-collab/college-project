package com.example.travelbuddy.model;


public class Geometry {
    private double[] coordinates;

    public double getLat() {
        return coordinates != null ? coordinates[1] : 0;
    }

    public double getLon() {
        return coordinates != null ? coordinates[0] : 0;
    }
}


//package com.example.travelbuddy.model;
//
//public class Geometry {
//    private String type;
//    private double[] coordinates;
//
//    public Geometry() {}
//
//    public String getType() {
//        return type;
//    }
//
//    public void setType(String type) {
//        this.type = type;
//    }
//
//    public double[] getCoordinates() {
//        return coordinates;
//    }
//
//    public void setCoordinates(double[] coordinates) {
//        this.coordinates = coordinates;
//    }
//
//    public double getLat() {
//        return coordinates != null && coordinates.length > 1 ? coordinates[1] : 0;
//    }
//
//    public double getLon() {
//        return coordinates != null && coordinates.length > 0 ? coordinates[0] : 0;
//    }
//}
