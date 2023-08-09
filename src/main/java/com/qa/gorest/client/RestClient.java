package com.qa.gorest.client;

import com.qa.gorest.frameworkexceptions.APIFrameworkException;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.Locale;
import java.util.Map;
import java.util.Properties;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;

public class RestClient {
    private final Properties properties;
    private final String baseURI;
    private final RequestSpecBuilder specBuilder;

    private boolean isAuthorizationHeaderAdded = false;

    public RestClient(Properties prop,String baseURI){
        specBuilder = new RequestSpecBuilder();
        this.properties = prop;
        this.baseURI = baseURI;
    }
    public void addAuthorizationHeader(){
        if(!isAuthorizationHeaderAdded){
            specBuilder.addHeader("Authorization","Bearer "+properties.getProperty("tokenId"));
            isAuthorizationHeaderAdded = true;
        }
    }
    private void setRequestContentType(String contentType){
        switch (contentType.toLowerCase(Locale.ROOT)){
            case "json":
                specBuilder.setContentType(ContentType.JSON);
                break;
            case "xml":
                specBuilder.setContentType(ContentType.XML);
                break;
            case "text":
                specBuilder.setContentType(ContentType.TEXT);
                break;
            default:
                String msg = "Please pass valid content type";
                throw new APIFrameworkException(msg);
        }
    }
    private RequestSpecification createRequestSpec(boolean includeAuth){
        specBuilder.setBaseUri(baseURI);
        if(includeAuth) addAuthorizationHeader();
        return specBuilder.build();
    }
    private RequestSpecification createRequestSpec(Map<String,String> headersMap,boolean includeAuth){
        specBuilder.setBaseUri(baseURI);
        if(includeAuth) addAuthorizationHeader();
        if(headersMap!=null) specBuilder.addHeaders(headersMap);
        return specBuilder.build();
    }
    private RequestSpecification createRequestSpec(Map<String,Object> queryMap,Map<String,String> headersMap,boolean includeAuth){
        specBuilder.setBaseUri(baseURI);
        if(includeAuth) addAuthorizationHeader();
        if(headersMap!=null) specBuilder.addHeaders(headersMap);
        if(queryMap!=null) specBuilder.addQueryParams(queryMap);
        return specBuilder.build();
    }
    private RequestSpecification createRequestSpec(String contentType,Object requestBody,boolean includeAuth){
        specBuilder.setBaseUri(baseURI);
        if(includeAuth) addAuthorizationHeader();
        setRequestContentType(contentType);
        if(requestBody!=null) specBuilder.setBody(requestBody);
        return specBuilder.build();
    }
    private RequestSpecification createRequestSpec(Map<String,String> headersMap,String contentType,Object requestBody,boolean includeAuth){
        specBuilder.setBaseUri(baseURI);
        if(includeAuth) addAuthorizationHeader();
        if(headersMap!=null) specBuilder.addHeaders(headersMap);
        setRequestContentType(contentType);
        if(requestBody!=null) specBuilder.setBody(requestBody);
        return specBuilder.build();
    }

    // HTTP Methods Utils
    public Response get(String serviceEndpoint, boolean includeAuth, boolean log){
        if(log) {
         return RestAssured.given(createRequestSpec(includeAuth)).log().all()
                    .when()
                    .get(serviceEndpoint);
        }
        return RestAssured.given(createRequestSpec(includeAuth))
                    .when()
                    .get(serviceEndpoint);
    }

    public Response get(String serviceEndpoint, Map<String,String> headersMap, boolean includeAuth,boolean log){
        if(log) {
            return RestAssured.given(createRequestSpec(headersMap,includeAuth)).log().all()
                    .when()
                    .get(serviceEndpoint);
        }
        return RestAssured.given(createRequestSpec(headersMap,includeAuth))
                .when()
                .get(serviceEndpoint);
    }
    public Response get(String serviceEndpoint, Map<String,Object> queryParams,Map<String,String> headersMap, boolean includeAuth,boolean log){
        if(log) {
            return RestAssured.given(createRequestSpec(queryParams,headersMap,includeAuth)).log().all()
                    .when()
                    .get(serviceEndpoint);
        }
        return RestAssured.given(createRequestSpec(queryParams,headersMap,includeAuth))
                .when()
                .get(serviceEndpoint);
    }

    public Response post(String serviceEndPoint,String contentType, Object requestBody, boolean includeAuth,boolean log){
        if(log){
            return RestAssured.given(createRequestSpec(contentType,requestBody,includeAuth)).log().all()
                    .when()
                    .post(serviceEndPoint);
        }
        return RestAssured.given(createRequestSpec(contentType,requestBody,includeAuth))
                .when()
                .post(serviceEndPoint);
    }

    public Response post(String serviceEndPoint,Map<String,String> headersMap,String contentType, Object requestBody,boolean includeAuth, boolean log){
        if(log){
            return RestAssured.given(createRequestSpec(headersMap,contentType,requestBody,includeAuth)).log().all()
                    .when()
                    .post(serviceEndPoint);
        }
        return RestAssured.given(createRequestSpec(headersMap,contentType,requestBody,includeAuth))
                .when()
                .post(serviceEndPoint);
    }

    public Response put(String serviceEndPoint,String contentType, Object requestBody, boolean includeAuth,boolean log){
        if(log){
            return RestAssured.given(createRequestSpec(contentType,requestBody,includeAuth)).log().all()
                    .when()
                    .put(serviceEndPoint);
        }
        return RestAssured.given(createRequestSpec(contentType,requestBody,includeAuth))
                .when()
                .put(serviceEndPoint);
    }

    public Response put(String serviceEndPoint,Map<String,String> headersMap,String contentType, Object requestBody, boolean includeAuth,boolean log){
        if(log){
            return RestAssured.given(createRequestSpec(headersMap,contentType,requestBody,includeAuth)).log().all()
                    .when()
                    .put(serviceEndPoint);
        }
        return RestAssured.given(createRequestSpec(headersMap,contentType,requestBody,includeAuth))
                .when()
                .put(serviceEndPoint);
    }

    public Response patch(String serviceEndPoint,String contentType, Object requestBody, boolean includeAuth,boolean log){
        if(log){
            return RestAssured.given(createRequestSpec(contentType,requestBody,includeAuth)).log().all()
                    .when()
                    .patch(serviceEndPoint);
        }
        return RestAssured.given(createRequestSpec(contentType,requestBody,includeAuth))
                .when()
                .patch(serviceEndPoint);
    }

    public Response patch(String serviceEndPoint,Map<String,String> headersMap,String contentType, Object requestBody, boolean includeAuth,boolean log){
        if(log){
            return RestAssured.given(createRequestSpec(headersMap,contentType,requestBody,includeAuth)).log().all()
                    .when()
                    .patch(serviceEndPoint);
        }
        return RestAssured.given(createRequestSpec(headersMap,contentType,requestBody,includeAuth))
                .when()
                .patch(serviceEndPoint);
    }

    public Response delete(String serviceEndPoint,boolean includeAuth,boolean log){
        if(log){
            return RestAssured.given(createRequestSpec(includeAuth)).log().all()
                    .when()
                    .delete(serviceEndPoint);
        }
        return RestAssured.given(createRequestSpec(includeAuth))
                .when()
                .delete(serviceEndPoint);
    }

    public String getAccessToken(String serviceEndpoint, String grantType, String clientId, String clientSecret){
        RestAssured.baseURI = "https://test.api.amadeus.com";
        String accessToken = given()
                .formParam("client_id",clientId)
                .formParam("client_secret",clientSecret)
                .formParam("grant_type",grantType).
                when()
                .post(serviceEndpoint).
                then()
                .assertThat()
                .statusCode(200)
                .extract()
                .path("access_token");

        System.out.println("Access Token: "+accessToken);
        return accessToken;
    }

}
