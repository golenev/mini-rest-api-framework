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
                .get("https://jsonplaceholder.typicode.com/posts")
                .then().log().all()
                .statusCode(200)
                .extract();
        List<UserData> users = response.then().extract().body().jsonPath().getList("", UserData.class);
        List<Integer> listOfIds = users.stream().map(x->x.getId()).collect(Collectors.toList());
       List<Integer> sortedList = listOfIds.stream().sorted().collect(Collectors.toList());
        //int i = 0;
        //listOfIds.stream().sorted().collect(Collectors.toList()).forEach(x ->Assert.assertEquals(x,listOfIds.get(i++)));
               for (int i = 0; i < listOfIds.size(); i++) {
                   System.out.println(listOfIds.get(i) + " " + sortedList.get(i));
                   Assert.assertEquals(listOfIds.get(i) , sortedList.get(i));

        }

        //Assert.assertEquals(users.get(4).getId(), listOfIds.get(4));
        Assert.assertEquals("application/json; charset=utf-8", response.getContentType());
        Assert.assertEquals(response.getStatusCode(), 200);
    }

    @Test
    public void getRequiredPostRequest() {
        //Specifications.installSpecification(Specifications.requestSpec(ConstantForApi.URL), Specifications.responseSpecOK200());
       Response response = (Response) given()
                .contentType(ContentType.JSON)
                .when()
                .get("https://jsonplaceholder.typicode.com/posts/99")
                .then().log().all()
                .statusCode(200)
                .extract().body().jsonPath().getList("", UserData.class);
       // List<UserData> users = response.then().extract()
       // System.out.println(users.size());
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

