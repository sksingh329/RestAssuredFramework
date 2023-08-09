package com.qa.gorest.base;

import com.qa.gorest.client.RestClient;
import com.qa.gorest.configuration.ConfigurationManager;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;

import java.util.Properties;

public class BaseTest {
    //Service URLs
    public static final String GOREST_ENDPOINT = "/public/v2/users";
    public static final String CIRCUIT_ENDPOINT = "/api/f1";
    public static final String REQRES_ENDPOINT = "/api/users";
    public static final String AMADEUS_TOKEN_ENDPOINT = "/v1/security/oauth2/token";
    public static final String AMADEUS_FLIGHTBOOKING_ENDPOINT = "/v1/shopping/flight-destinations";
    public static final String FAKESTORE_ENDPOINT = "/products";



    protected Properties prop;
    protected RestClient restClient;
    protected String baseURI;

    @Parameters({"baseURI"})
    @BeforeTest
    protected void setUp(String baseURI){
        RestAssured.filters(new AllureRestAssured());
        ConfigurationManager config = new ConfigurationManager();
        prop = config.initProp();
        this.baseURI = baseURI;
    }
}
