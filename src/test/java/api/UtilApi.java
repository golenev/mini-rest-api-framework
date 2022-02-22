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

}
