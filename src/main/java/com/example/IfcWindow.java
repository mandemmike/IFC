package com.example;

public class IfcWindow {

    private String id;
    private double OverallWidth;
    private double OverallHeight;
    private String Placement_ref;


    public IfcWindow(String id, String OverallWidth, String OverallHeight, String Placement_Ref){
        this.id = id;
        this.OverallWidth = Double.parseDouble(OverallWidth);
        this.OverallHeight = Double.parseDouble(OverallHeight);
        this.Placement_ref = Placement_Ref;
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
