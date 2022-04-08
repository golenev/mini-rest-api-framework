package api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import models.posts.PostsData;
import java.util.List;
import static io.restassured.RestAssured.given;

public class UtilApi {
    private static RequestSpecification prepareResponse () {
        return given()
               .contentType(ContentType.JSON)
               .when();
            }

    public static Response useMethodGet (String url)  {
        return prepareResponse().get(url);
    }

    public static Response useMethodPost (String url, Object body)  {
        return prepareResponse().contentType(ContentType.JSON).body(body).when().post(url);
    }

    public static Response postWithParam(String url, String queryKey, Object queryValue){
        return prepareResponse().contentType(ContentType.JSON).queryParam(queryKey, queryValue).when().post(url);
    }


    public static Response useMethodGetWithParam (String url)  {
        return given().queryParam("apiKey", "08a1232811b6465b8fd70bcf2d549a16")

                .contentType(ContentType.JSON)
                .when().get(url);
    }

   /* public static Response postWithParam(){
        return given().auth().basic("login", "password").when().post("http://localhost:8080/api/token/get?variant=2")
                .then().statusCode(200).extract().body().asString();
    }*/


}
