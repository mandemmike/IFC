package com.example;

public class Ifc_Door {

    private String id;
    private double OverallWidth;
    private double OverallHeight;
    private String Placement_ref;


    public Ifc_Door(String id, String OverallWidth, String OverallHeight, String Placement_ref){
        this.id = id;
        this.OverallWidth = Double.parseDouble(OverallWidth);
        this.OverallHeight = Double.parseDouble(OverallHeight);
        this.Placement_ref = Placement_ref;
    }

    public String getId() {
        return id;
    }
    
    public String getPlacement_ref() {
        return Placement_ref;
    }
    
    public double getOverallHeight() {
        return OverallHeight;
    }

    public double getOverallWidth() {
        return OverallWidth;
    }


    
}
