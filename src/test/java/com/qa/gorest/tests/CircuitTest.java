package com.qa.gorest.tests;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIHttpStatus;
import com.qa.gorest.utils.JsonPathValidator;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class CircuitTest extends BaseTest {
    @BeforeMethod
    public void setup(){
        restClient = new RestClient(prop,baseURI);
    }

    @DataProvider
    public Object[][] getCircuitYear(){
        return new Object[][] {
                {2015},
                {2016},
                {2017}
        };
    }

    @Test(dataProvider = "getCircuitYear",enabled = true)
    public void getCircuitTest(int circuitYear){
        Response circuitResponse = restClient.get(CIRCUIT_ENDPOINT+"/"+circuitYear+"/circuits.json",false,true);

        int statusCode = circuitResponse.getStatusCode();
        Assert.assertEquals(statusCode,APIHttpStatus.OK_200.getCode());

        JsonPathValidator js = new JsonPathValidator(circuitResponse);
        List<String> countryList = js.readList("$.MRData.CircuitTable.Circuits[?(@.circuitId=='americas')].Location.country");

        Assert.assertEquals(countryList.get(0),"USA");
    }
}
