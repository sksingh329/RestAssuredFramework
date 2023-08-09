package com.qa.gorest.tests;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIHttpStatus;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.hamcrest.Matchers;

import java.util.HashMap;
import java.util.Map;

public class GetUserTest extends BaseTest {
    @BeforeMethod
    public void setup(){
        restClient = new RestClient(prop,baseURI);
    }
    @Test
    public void getAllUsersTest(){
        restClient.get(GOREST_ENDPOINT,true,true)
                .then().log().all()
                .assertThat()
                .statusCode(APIHttpStatus.OK_200.getCode());
    }
    @Test
    public void getUserTest(){
        int userId = 4235210;
        restClient.get(GOREST_ENDPOINT+"/"+userId,true,true)
                .then().log().all()
                .assertThat()
                .statusCode(APIHttpStatus.OK_200.getCode())
                .and()
                .body("id",Matchers.equalTo(userId));
    }
    @DataProvider
    public Object[][] getUserTestData(){
        return new Object[][]{
                {"Subodh","active"},
                {"Ravi","inactive"},
                {"Sam","active"}
        };
    }
    @Test(dataProvider = "getUserTestData")
    public void getUserWithQueryParamsTest(String name,String status){
        Map<String,Object> queryParams = new HashMap<>();
        queryParams.put("name",name);
        queryParams.put("status",status);

        restClient.get(GOREST_ENDPOINT,queryParams,null,true,true)
                .then().log().all()
                .assertThat()
                .statusCode(APIHttpStatus.OK_200.getCode());
    }
}
