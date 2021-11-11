package com.example;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.Document;
import javax.xml.bind.JAXBContext;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpression;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.apache.xerces.impl.xpath.XPath;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import ch.qos.logback.core.read.ListAppender;  

public class App 
{
    JAXBContext j;
    private static ArrayList<Ifc_Door> doors = new ArrayList<>();
    private static ArrayList<IfcWindow> windows = new ArrayList<>();
    public static void main( String[] args ) throws ParserConfigurationException, XPathExpressionException, SAXException, IOException
    {
       // XmlToObjects converter = new XmlToObjects();
       // converter.converter();
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

    // Xpath expressions 
    // doors
    String door_id_expr = "/iso_10303_28/uos//IfcDoor//GlobalId";
    String door_widht_expr = "/iso_10303_28/uos//IfcDoor//OverallWidth";
    String door_height_expr = "/iso_10303_28/uos//IfcDoor//OverallHeight";
    
    // windows
    String windows_id_expr = "/iso_10303_28/uos//IfcWindow//GlobalId";
    String window_widht_expr = "/iso_10303_28/uos//IfcWindow//OverallWidth";
    String window_height_expr = "/iso_10303_28/uos//IfcWindow//OverallHeight";

    // walls
    String walls_id_expr = "/iso_10303_28/uos//IfcWallStandardCase//GlobalId";
    String walls_widht_expr = "/iso_10303_28/uos//IfcWallStandardCase//OverallWidth";
    String walls_height_expr = "/iso_10303_28/uos//IfcWallStandardCase//OverallHeight";

    // door elements
    NodeList door_ids = (NodeList) xpathFactory.compile(door_id_expr).evaluate(xmlDocument, XPathConstants.NODESET);    
    NodeList door_height_list = (NodeList) xpathFactory.compile(door_height_expr).evaluate(xmlDocument, XPathConstants.NODESET);
    NodeList door_widht_list = (NodeList) xpathFactory.compile(door_widht_expr).evaluate(xmlDocument, XPathConstants.NODESET);

    // window elements
    NodeList window_ids = (NodeList) xpathFactory.compile(windows_id_expr)
    .evaluate(xmlDocument, XPathConstants.NODESET);    

    NodeList window_height_list = (NodeList) xpathFactory.compile(window_widht_expr)
    .evaluate(xmlDocument, XPathConstants.NODESET);

    NodeList window_widht_list = (NodeList) xpathFactory.compile(window_height_expr)
    .evaluate(xmlDocument, XPathConstants.NODESET);

    for(int i = 0; i < Math.min(door_ids.getLength(), window_widht_list.getLength()); i++){
        // add new instance of door to list
        doors.add(new Ifc_Door(door_ids.item(i).getFirstChild().getNodeValue(), 
        door_widht_list.item(i).getFirstChild().getNodeValue(), 
        door_height_list.item(i).getFirstChild().getNodeValue()));

        // add new instance of window to list
        windows.add(new IfcWindow(window_ids.item(i).getFirstChild().getNodeValue(), 
        window_widht_list.item(i).getFirstChild().getNodeValue(), 
        window_height_list.item(i).getFirstChild().getNodeValue()));

    }




    for(int j = 0; j<doors.size();j++){
        System.out.println("door id " + doors.get(j).getId() +
        " height " + doors.get(j) .getOverallHeight() +
        " width " + doors.get(j).getOverallWidth()); ;

        System.out.println("window id " + windows.get(j).getId() +
        " height " + windows.get(j) .getOverallHeight() +
        " width " + windows.get(j).getOverallWidth()); ;
    }



}
}
