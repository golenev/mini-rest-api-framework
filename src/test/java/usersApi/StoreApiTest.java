package usersApi;

import org.testng.Assert;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
public class StoreApiTest {


    @Test
    public void getAllPostsRequest (){
        Specifications.installSpecification(Specifications.requestSpec(ConstantForApi.URL), Specifications.responseSpecOK200());
        given()
                .when()
                .get(String.valueOf(ConstantForApi.SECTION_POSTS))
                .then().log().all()
                .extract().body().jsonPath();
    }

    @Test
    public void getRequiredPostRequest (){
        Specifications.installSpecification(Specifications.requestSpec(ConstantForApi.URL), Specifications.responseSpecOK200());
        given()
                .when()
                .get(String.valueOf(StringUtils.createString(ConstantForApi.SECTION_POSTS, ConstantForApi.INDEX_FOR_POSTS)))
                .then().log().all()
                .extract().body().jsonPath();
    }

    @Test
    public void getNotExistenceResponse (){
        Specifications.installSpecification(Specifications.requestSpec(ConstantForApi.URL), Specifications.responseSpecOK404());
        given()
                .when()
                .get(String.valueOf(StringUtils.createString(ConstantForApi.SECTION_POSTS, ConstantForApi.INDEX_THAT_NOT_EXIST)))
                .then().log().all()
                .extract().body().jsonPath();
    }


    @Test
    public void postRequestToApi (){
        Specifications.installSpecification(Specifications.requestSpec(ConstantForApi.URL), Specifications.responseSpecOK201());
        String title = "AlexExample";
        UserForTest userForTest = new UserForTest("AlexExample", "example_body", 1, 101);
        SuccessReg successReg = given()
                .body(userForTest)
                .when()
                .post(String.valueOf(ConstantForApi.SECTION_POSTS))
                .then().log().all()
                .extract().as(SuccessReg.class);
        Assert.assertEquals(title, successReg.getTitle());

    }



    @Test
    public void getAllUsersRequest (){
        Specifications.installSpecification(Specifications.requestSpec(ConstantForApi.URL), Specifications.responseSpecOK200());
         given()
                .when()
                .get(String.valueOf(ConstantForApi.SECTION_USERS))
                .then().log().all()
                .extract().body().jsonPath();
    }

    @Test
    public void getRequiredUserRequest (){
        Specifications.installSpecification(Specifications.requestSpec(ConstantForApi.URL), Specifications.responseSpecOK200());
        given()
                .when()
                .get(String.valueOf(StringUtils.createString(ConstantForApi.SECTION_USERS, ConstantForApi.INDEX_FOR_USERS)))
                .then().log().all()
                .extract().body().jsonPath();
    }

}

