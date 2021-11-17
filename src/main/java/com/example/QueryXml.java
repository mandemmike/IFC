package com.example;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.text.Document;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class QueryXml {

    private static ArrayList<Ifc_Door> doors = new ArrayList<>();
    private static ArrayList<IfcWindow> windows = new ArrayList<>();
    private static ArrayList<Ifc_location> locations = new ArrayList<>();

    public QueryXml(){}

    public void QueryAndConvert() throws FileNotFoundException, SAXException, IOException, ParserConfigurationException, XPathExpressionException{
        String xml = "/Users/mmm/demo/src/resources/Testhus-Modifierad.xml";
 

    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//  factory.setNamespaceAware(true);
    DocumentBuilder builder;
    Document doc = null;
    builder = factory.newDocumentBuilder();
    org.w3c.dom.Document xmlDocument;

    xmlDocument = builder.parse(
        new FileInputStream("/Users/mmm/demo/src/resources/Testhus-Modifierad.xml"));

    javax.xml.xpath.XPath xpathFactory = XPathFactory.newInstance().newXPath();

    // Xpath expressions to query xml document

    // doors
    String door_id_expr = "/iso_10303_28/uos//IfcDoor//GlobalId";
    String door_widht_expr = "/iso_10303_28/uos//IfcDoor//OverallWidth";
    String door_height_expr = "/iso_10303_28/uos//IfcDoor//OverallHeight";
    String door_LocalPlace_ref = "/iso_10303_28/uos//IfcDoor//ObjectPlacement//IfcLocalPlacement//@ref";


    // windows
    String windows_id_expr = "/iso_10303_28/uos//IfcWindow//GlobalId";
    String window_widht_expr = "/iso_10303_28/uos//IfcWindow//OverallWidth";
    String window_height_expr = "/iso_10303_28/uos//IfcWindow//OverallHeight";
    String window_LocalPlace_ref = "/iso_10303_28/uos//IfcWindow//ObjectPlacement//IfcLocalPlacement/@ref";
/*
    // walls
    String walls_id_expr = "/iso_10303_28/uos//IfcWallStandardCase//GlobalId";
    String walls_widht_expr = "/iso_10303_28/uos//IfcWallStandardCase//ObjectPlacement//IfcLocalPlacement";
    String walls_height_expr = "/iso_10303_28/uos//IfcWallStandardCase//OverallHeight//IfcLocalPlacement";
*/
    // location values
    String ifc_location = "/iso_10303_28/uos/IfcLocalPlacement/RelativePlacement/IfcAxis2Placement3D/Location/IfcCartesianPoint/Coordinates//IfcLengthMeasure";
    String ifc_axis_id = "/iso_10303_28/uos//IfcLocalPlacement//RelativePlacement//IfcAxis2Placement3D//@id";
    String ifc_cartesian_id = "/iso_10303_28/uos//IfcLocalPlacement//RelativePlacement//IfcAxis2Placement3D//Location//IfcCartesianPoint//@id";
    String ifc_localPlacement_id = "/iso_10303_28/uos//IfcLocalPlacement//@id";
    
    NodeList ifc_axis_id_list = (NodeList) xpathFactory.compile(ifc_axis_id)
    .evaluate(xmlDocument, XPathConstants.NODESET);

    NodeList ifc_cartesian_id_list = (NodeList) xpathFactory.compile(ifc_cartesian_id)
    .evaluate(xmlDocument, XPathConstants.NODESET);

    NodeList ifc_localPLacement_id_list = (NodeList) xpathFactory.compile(ifc_localPlacement_id)
    .evaluate(xmlDocument, XPathConstants.NODESET);

    NodeList location_list = (NodeList) xpathFactory.compile(ifc_location)
    .evaluate(xmlDocument, XPathConstants.NODESET);

    // door elements
    NodeList door_ids = (NodeList) xpathFactory.compile(door_id_expr)
    .evaluate(xmlDocument, XPathConstants.NODESET);    
    NodeList door_LocalPlace_ref_list = (NodeList) xpathFactory.compile(door_LocalPlace_ref)
    .evaluate(xmlDocument, XPathConstants.NODESET);

    NodeList door_height_list = (NodeList) xpathFactory.compile(door_height_expr)
    .evaluate(xmlDocument, XPathConstants.NODESET);

    NodeList door_widht_list = (NodeList) xpathFactory.compile(door_widht_expr)
    .evaluate(xmlDocument, XPathConstants.NODESET);

    // window elements
    NodeList window_ids = (NodeList) xpathFactory.compile(windows_id_expr)
    .evaluate(xmlDocument, XPathConstants.NODESET);
    NodeList window_LocalPlace_ref_list = (NodeList) xpathFactory.compile(window_LocalPlace_ref)
    .evaluate(xmlDocument, XPathConstants.NODESET);    

    NodeList window_height_list = (NodeList) xpathFactory.compile(window_widht_expr)
    .evaluate(xmlDocument, XPathConstants.NODESET);

    NodeList window_widht_list = (NodeList) xpathFactory.compile(window_height_expr)
    .evaluate(xmlDocument, XPathConstants.NODESET);


    for(int i = 0; i < door_ids.getLength(); i++){
        // add new instance of door to list
        doors.add(new Ifc_Door(door_ids.item(i).getFirstChild().getNodeValue(), 
        door_widht_list.item(i).getFirstChild().getNodeValue(), 
        door_height_list.item(i).getFirstChild().getNodeValue(),
        door_LocalPlace_ref_list.item(i).getFirstChild().getNodeValue())); 
    }
       

    for(int m = 0; m < window_ids.getLength(); m++){
        // add new instance of window to list
        windows.add(new IfcWindow(window_ids.item(m).getFirstChild().getNodeValue(), 
        window_widht_list.item(m).getFirstChild().getNodeValue(), 
        window_height_list.item(m).getFirstChild().getNodeValue(),
        window_LocalPlace_ref_list.item(m).getFirstChild().getNodeValue()));
    }

    // identify each localPlacement element and put them into list as an instance of an object
    double x = 0; double y = 0; double z = 0;

    for(int k= 0; k < location_list.getLength();k++){
        for(int s = k; s < k+2; s++){
            if(s == k && location_list.item(s) != null){
                x = Double.parseDouble(location_list.item(s).getFirstChild().getNodeValue());
            } else if(s == k+1 && location_list.item(s) != null){
                y = Double.parseDouble(location_list.item(s).getFirstChild().getNodeValue());
            } else if(s == k+2 && location_list.item(s) != null) {
                z = Double.parseDouble(location_list.item(s).getFirstChild().getNodeValue());
            }
        }

        try {
            locations.add(new Ifc_location(ifc_localPLacement_id_list.item(k).getFirstChild().getNodeValue(), 
            ifc_axis_id_list.item(k).getFirstChild().getNodeValue(), 
            ifc_cartesian_id_list.item(k).getFirstChild().getNodeValue(), 
            x, y, z));
            
        } catch (NullPointerException e) {
            System.out.println("No item found for some element in position " + k);
        }
      
    }

    // CmdL printing for dev purposes

    for(int j = 0; j<doors.size();j++){
        System.out.println("door id " + doors.get(j).getId() +
        " height " + doors.get(j) .getOverallHeight() +
        " width " + doors.get(j).getOverallWidth() + 
        " ref " + doors.get(j).getPlacement_ref()); 

    }

    for(int j = 0; j<windows.size();j++){
        System.out.println("window id " + windows.get(j).getId() +
        " height " + windows.get(j).getOverallHeight() +
        " width " + windows.get(j).getOverallWidth() +  
        " ref " +  windows.get(j).getPlacement_ref()); 

    }

    for(int p = 0; p < locations.size(); p++){
        System.out.println("locations " + locations.get(p).getLocationPlacement_id() + " " + locations.get(p).getAxis_id() + " " +
        locations.get(p).getCartesian_id() + " " + locations.get(p).getCord_x() + " " + locations.get(p).getCord_y() + 
        " " + locations.get(p).getCord_z());
    }
    }
    
}
