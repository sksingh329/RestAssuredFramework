package com.qa.gorest.utils;

import com.jayway.jsonpath.JsonPath;
import com.jayway.jsonpath.PathNotFoundException;
import com.qa.gorest.frameworkexceptions.APIFrameworkException;
import io.restassured.response.Response;

import java.util.List;
import java.util.Map;

public class JsonPathValidator {
    private String responseString;

    public JsonPathValidator(Response response){
        responseString = response.getBody().asString();
    }

    public <T> T read(String jsonPath){
        try{
            return JsonPath.read(responseString,jsonPath);
        }
        catch (PathNotFoundException ex){
            ex.printStackTrace();
            throw new APIFrameworkException("Path is not not found");
        }
    }

    public <T> List<T> readList(String jsonPath){
        try{
            return JsonPath.read(responseString,jsonPath);
        }
        catch (PathNotFoundException ex){
            ex.printStackTrace();
            throw new APIFrameworkException("Path is not not found");
        }
    }

    public <T> List<Map<String,T>> readListListOfMaps(String jsonPath){
        try{
            return JsonPath.read(responseString,jsonPath);
        }
        catch (PathNotFoundException ex){
            throw new APIFrameworkException("Path is not not found");
        }
    }
}
