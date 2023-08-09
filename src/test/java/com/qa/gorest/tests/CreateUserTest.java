package com.qa.gorest.tests;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIHttpStatus;
import com.qa.gorest.pojo.User;
import com.qa.gorest.utils.StringUtils;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CreateUserTest extends BaseTest {
    @BeforeMethod
    public void setup(){
        restClient = new RestClient(prop,baseURI);
    }

    @DataProvider
    public Object[][] getUserTestData(){
        return new Object[][]{
            {"Subodh","male","active"},
            {"Ravi","male","inactive"},
            {"Sam","male","active"}
        };
    }

    @Test(dataProvider = "getUserTestData")
    public void createUserTest(String name,String gender, String status){
        User user = new User(name, StringUtils.getRandomEmailId(name,"@test.com"),gender,status);

        int userId = restClient.post(GOREST_ENDPOINT,"json",user,true,true)
                .then().log().all()
                .assertThat()
                .statusCode(APIHttpStatus.CREATED_201.getCode())
                .extract().path("id");

        RestClient getRestClient = new RestClient(prop,baseURI);
        getRestClient.get(GOREST_ENDPOINT+"/"+userId,true,true)
                .then().log().all()
                .assertThat()
                .statusCode(APIHttpStatus.OK_200.getCode())
                .and()
                .body("id", Matchers.equalTo(userId));
    }
}
