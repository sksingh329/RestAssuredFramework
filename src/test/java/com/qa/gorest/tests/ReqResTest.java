package com.qa.gorest.tests;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIHttpStatus;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class ReqResTest extends BaseTest {
    @BeforeMethod
    public void setup(){
        restClient = new RestClient(prop,baseURI);
    }
    @DataProvider
    public Object[][] getUserId(){
        return new Object[][]{
                {"1"},
                {"2"},
                {"3"}
        };
    }
    @Test(dataProvider = "getUserId")
    public void getUserTest(String userId){
        restClient.get(REQRES_ENDPOINT+"/"+userId,false,true)
                .then().log().all()
                .assertThat()
                .statusCode(APIHttpStatus.OK_200.getCode());
    }
}
