package com.qa.gorest.tests;

import com.qa.gorest.base.BaseTest;
import com.qa.gorest.client.RestClient;
import com.qa.gorest.constants.APIHttpStatus;
import com.qa.gorest.pojo.Product;
import org.hamcrest.Matchers;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class FakeStoreAPITest extends BaseTest {
    @BeforeMethod
    public void setup(){
        restClient = new RestClient(prop,baseURI);
    }
    @DataProvider
    public Object[][] getProductTestData(){
        return new Object[][]{
                {15.5f,"android phone","old phone","https://i.pravatar.cc","electronic"},
                {50.0f,"bicycle","new bike","https://i.pravatar.cc","bike"},
                {100f,"IFB washing machine","washing machine","https://i.pravatar.cc","electronic"}
        };
    }
    @Test(dataProvider = "getProductTestData")
    public void createProductTest(Float price,String title,String description,String image,String category){
        Product product = new Product(price,title,description,image,category);
        restClient.post(FAKESTORE_ENDPOINT,"json",product,false,true)
                .then().log().all()
                .assertThat()
                .statusCode(APIHttpStatus.OK_200.getCode())
                .and()
                .body("title", Matchers.equalTo(title));
    }
    @Test
    public void getAllProductsTest(){
        restClient.get(FAKESTORE_ENDPOINT,false,true)
                .then().log().all()
                .assertThat()
                .statusCode(APIHttpStatus.OK_200.getCode());
    }
    @DataProvider
    public Object[][] getProductId(){
        return new Object[][]{
                {1},
                {2},
                {3}
        };
    }
    @Test(dataProvider = "getProductId")
    public void getSingleProductTest(int productId){
        restClient.get(FAKESTORE_ENDPOINT+"/"+productId,false,true)
                .then().log().all()
                .assertThat()
                .statusCode(200);
    }
}
