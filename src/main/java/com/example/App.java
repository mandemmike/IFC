package com.example;

import java.io.FileNotFoundException;
import java.io.IOException;


import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPathExpressionException;
import org.xml.sax.SAXException;

import ch.qos.logback.core.pattern.parser.Node;


public class App 
{
  
    public static void main( String[] args ) throws FileNotFoundException, XPathExpressionException, SAXException, IOException, ParserConfigurationException
    {
        QueryXml query = new QueryXml();
        query.QueryAndConvert();
     
    }
}
