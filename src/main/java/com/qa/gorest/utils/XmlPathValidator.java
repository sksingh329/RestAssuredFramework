package com.qa.gorest.utils;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import com.qa.gorest.frameworkexceptions.APIFrameworkException;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

public class XmlPathValidator {
    private String responseString;
    private XmlPath xmlPath;
    public XmlPathValidator(Response response){
        responseString = response.getBody().asString();
        xmlPath = new XmlPath(responseString);
    }

    public <T> T read(String xmlPathExpression){
        try{
            return xmlPath.get(xmlPathExpression);
        }
        catch (PathNotFoundException ex){
            ex.printStackTrace();
            throw new APIFrameworkException("Path is not not found");
        }
    }

    public <T> List<T> readList(String xmlPathExpression){
        try{
            return xmlPath.get(xmlPathExpression);
        }
        catch (PathNotFoundException ex){
            ex.printStackTrace();
            throw new APIFrameworkException("Path is not not found");
        }
    }

    public <T> List<Map<String,T>> readListListOfMaps(String xmlPathExpression){
        try{
            return xmlPath.get(xmlPathExpression);
        }
        catch (PathNotFoundException ex){
            throw new APIFrameworkException("Path is not not found");
        }
    }

}
