package com.qa.gorest.tests;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIHttpStatus;
import jdk.jfr.DataAmount;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

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

    @Test(dataProvider = "getCircuitYear")
    public void getCircuitTest(int circuitYear){
        restClient.get(CIRCUIT_ENDPOINT+"/"+circuitYear+"/circuits.json",false,true)
                .then().log().all()
                .assertThat()
                .statusCode(APIHttpStatus.OK_200.getCode());
    }
}
