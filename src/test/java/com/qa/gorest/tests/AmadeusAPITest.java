package com.qa.gorest.tests;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIHttpStatus;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.equalTo;

public class AmadeusAPITest extends BaseTest {

    private String accessToken;

    @Parameters({"grantType","clientId","clientSecret"})
    @BeforeMethod
    public void flightApiSetup(String grantType, String clientId, String clientSecret){
        restClient = new RestClient(prop,baseURI);
        accessToken = restClient.getAccessToken(AMADEUS_TOKEN_ENDPOINT,grantType,clientId,clientSecret);
    }
    @DataProvider
    public Object[][] getFlightSearchTestData(){
        return new Object[][] {
                {"PAR",100},
                {"LON",400},
                {"PAR",200}
        };
    }
    @Test(dataProvider = "getFlightSearchTestData")
    public void getFlightInfoTest(String origin, int maxPrice){
        Map<String,Object> queryParamMap = new HashMap<>();
        queryParamMap.put("origin",origin);
        queryParamMap.put("maxPrice",maxPrice);
        Map<String,String> headerMap = new HashMap<>();
        headerMap.put("Authorization","Bearer "+accessToken);
        RestClient flightRestClient = new RestClient(prop,baseURI);
        Response flightResponse = flightRestClient.get(AMADEUS_FLIGHTBOOKING_ENDPOINT,queryParamMap,headerMap,false,true)
                .then().log().all()
                .assertThat()
                .statusCode(APIHttpStatus.OK_200.getCode())
                .and()
                .extract().response();
        JsonPath js = flightResponse.jsonPath();
        String type  = js.getString("data[0].type");
        System.out.println(type);
    }
}
