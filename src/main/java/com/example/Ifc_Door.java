package com.example;

public class Ifc_Door {

    private String id;
    private double OverallWidth;
    private double OverallHeight;

    public Ifc_Door(String id, String OverallWidth, String OverallHeight){
        this.id = id;
        this.OverallWidth = Double.parseDouble(OverallWidth);
        this.OverallHeight = Double.parseDouble(OverallHeight);
    }

    public String getId() {
        return id;
    }

    public double getOverallHeight() {
        return OverallHeight;
    }

    public double getOverallWidth() {
        return OverallWidth;
    }


    
}
