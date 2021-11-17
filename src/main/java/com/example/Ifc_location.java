package com.example;

public class Ifc_location {
    private String LocationPlacement_id;
    private String axis_id;
    private String cartesian_id;
    private double cord_x;
    private double cord_y;
    private double cord_z;

    public Ifc_location(String LocationPlacement_id, String axis_id, String cartesian_id, double cord_x, double cord_y, double cord_z){
        this.LocationPlacement_id = LocationPlacement_id;
        this.axis_id = axis_id;
        this.cartesian_id = cartesian_id;
        this.cord_x = cord_x;
        this.cord_y = cord_y;
        this.cord_z = cord_z;

    }

    public double getCord_x() {
        return cord_x;
    }

    public double getCord_y() {
        return cord_y;
    }

    public double getCord_z() {
        return cord_z;
    }

    public String getAxis_id() {
        return axis_id;
    }

    public String getCartesian_id() {
        return cartesian_id;
    }

    public String getLocationPlacement_id() {
        return LocationPlacement_id;
    }
    
}
