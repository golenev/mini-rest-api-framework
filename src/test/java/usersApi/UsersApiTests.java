package usersApi;

import ApiUtils.ConstantForApi;
import ApiUtils.Specifications;
import ApiUtils.StringUtils;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.lang.reflect.Type;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;

public class UsersApiTests {




    @Test
    public void getAllPostsRequest() {
       // Specifications.installSpecification(Specifications.requestSpec(ConstantForApi.URL), Specifications.responseSpecOK200());
             // List<UserData> users = given()
        Response response = (Response) given()
                .contentType(ContentType.JSON)
                .when()
                .contentType(ContentType.JSON)
                .get("https://jsonplaceholder.typicode.com/posts")
                .then().log().all()
                .statusCode(200)
                .extract();
        List<UserData> users = response.then().extract().body().jsonPath().getList("", UserData.class);
        List<Integer> sortedList = users.stream().map(x->x.getId()).collect(Collectors.toList());
               for (Integer i = 0; i < users.size(); i++) {
            System.out.println(users.get(i).getId());
        }
                sortedList.stream().sorted().collect(Collectors.toList());
        for (Integer i = 0; i < sortedList.size(); i++) {
            System.out.print(sortedList.get(i));
        }
        Assert.assertEquals(users.get(4).getId(), sortedList.get(4));
        Assert.assertEquals("application/json; charset=utf-8", response.getContentType());
        Assert.assertEquals(response.getStatusCode(), 200);


    }

    @Test
    public void getRequiredPostRequest() {
        //Specifications.installSpecification(Specifications.requestSpec(ConstantForApi.URL), Specifications.responseSpecOK200());
       Response response = given()
                .contentType(ContentType.JSON)
                .when()
                .get("https://jsonplaceholder.typicode.com/posts/99")
                .then().log().all()
                //.statusCode(200)
                .extract()
                .response();
                Assert.assertEquals("application/json; charset=utf-8", response.getContentType());
                Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void getNotExistenceResponse() {
        Specifications.installSpecification(Specifications.requestSpec(ConstantForApi.URL), Specifications.responseSpecOK404());
        given()
                .when()
                .get(String.valueOf(StringUtils.createString(ConstantForApi.SECTION_POSTS, ConstantForApi.INDEX_THAT_NOT_EXIST)))
                .then().log().all()
                .extract().body().jsonPath();
    }


    @Test
    public void postRequestToApi() {
        Specifications.installSpecification(Specifications.requestSpec(ConstantForApi.URL), Specifications.responseSpecOK201());
        String title = "AlexExample";
        UserData userData = new UserData("AlexExample", "example_body", 1, 101);

        ExpectedResultUsersClass expectedResultUsersClass = given()
                .body(userData)
                .when()
                .post(String.valueOf(ConstantForApi.SECTION_POSTS))
                .then().log().all()
                .extract().as(ExpectedResultUsersClass.class);
        Assert.assertEquals(title, expectedResultUsersClass.getTitle());




    }


    @Test
    public void getAllUsersRequest() {
        Specifications.installSpecification(Specifications.requestSpec(ConstantForApi.URL), Specifications.responseSpecOK200());
        given()
                .when()
                .get(String.valueOf(ConstantForApi.SECTION_USERS))
                .then().log().all()
                .extract().body().jsonPath();
    }

    @Test
    public void getRequiredUserRequest() {
        Specifications.installSpecification(Specifications.requestSpec(ConstantForApi.URL), Specifications.responseSpecOK200());
        given()
                .when()
                .get(String.valueOf(StringUtils.createString(ConstantForApi.SECTION_USERS, ConstantForApi.INDEX_FOR_USERS)))
                .then().log().all()
                .extract().body().jsonPath();
    }

}

