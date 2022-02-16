package usersApi;

import OtherUtils.StringUtils;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static usersApi.JsonReader.*;
import static usersApi.UtilApi.*;
public class UsersApiTests {




    @Test
    public void getAllPostsRequest() {
        //https://jsonplaceholder.typicode.com/posts
        String url = getPostsUrl();
        Response response = useMethodGet(url);
        response.then().log().all();
        List<PostsData> users = response.then().extract().body().jsonPath().getList("", PostsData.class);
        List<Integer> listOfIds = users.stream().map(x->x.getId()).collect(Collectors.toList());
        List<Integer> sortedList = listOfIds.stream().sorted().collect(Collectors.toList());
               for (int i = 0; i < listOfIds.size(); i++) {
                   Assert.assertEquals(listOfIds.get(i) , sortedList.get(i));
        }
        Assert.assertEquals("application/json; charset=utf-8", response.getContentType());
        Assert.assertEquals(response.getStatusCode(), 200);
            }

    @Test
    public void getRequestToRequiredMessage() {
        //https://jsonplaceholder.typicode.com/posts/99
        String url = getPostsUrl(99);
        Response response = useMethodGet(url);
        response.then().log().all();
        Assert.assertEquals("application/json; charset=utf-8", response.getContentType());
        PostsData user = response.then().extract().body().jsonPath().getObject("", PostsData.class);
        response.then().log().all();
        Assert.assertEquals(user.getId(), 99);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(user.getUserId(), 10);
        Assert.assertEquals(user.getId(), 99);
        Assert.assertNotEquals(user.getTitle(), "");
        Assert.assertNotEquals(user.getBody(), "");
    }

    @Test
    //https://jsonplaceholder.typicode.com/posts/150 404
    public void getNotExistenceResponse() {
        String url = getPostsUrl(150);
        Response response = useMethodGet(url);
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 404);

    }


    @Test
    //https://jsonplaceholder.typicode.com/posts/
    public void postRequestToApi() {
        Response response = useMethodPost(getPostsUrl());
        UtilApi.installSpecification(UtilApi.requestSpec(), UtilApi.responseSpecOK201());
        PostsData postsData = new PostsData(StringUtils.generateRandomString(), StringUtils.generateLastName(), 1, 101);
        ExpectedResultUsersClass expectedResultUsersClass =  given()
                .body(postsData)
                .post(getPostsUrl())
                .then().log().all()
                .extract().as(ExpectedResultUsersClass.class);

        Assert.assertEquals(response.getStatusCode(), 201);
        Assert.assertEquals(postsData.getBody(), expectedResultUsersClass.getBody());
        Assert.assertEquals(postsData.getTitle(), expectedResultUsersClass.getTitle());




    }


    @Test
    public void getAllUsersRequest() {

        //https://jsonplaceholder.typicode.com/users
        String url = getUsersUrl();
        Response response = useMethodGet(url);
        //response.then().log().all();
        List<UsersData> users = response.then().extract().body().jsonPath().getList("", UsersData.class);
        System.out.println(users.get(4).getAddress().getStreet());
        System.out.println(users.get(4).getAddress().getSuite());
        System.out.println(users.get(4).getAddress().getCity());
        System.out.println(users.get(4).getAddress().getZipcode());
        System.out.println(users.get(4).getAddress().getGeo().getLat());
        System.out.println(users.get(4).getAddress().getGeo().getLng());
        System.out.println(users.get(4).getName());
        System.out.println(users.get(4).getUsername());
        System.out.println(users.get(4).getEmail());
        System.out.println(users.get(4).getCompany().getBs());



    }

    @Test
    public void getRequiredUserRequest() {
        //https://jsonplaceholder.typicode.com/users/5
        String url = getUsersUrl(5);
        Response response = useMethodGet(url);
        response.then().log().all();
        //List<UsersData> users = response.then().extract().body().jsonPath().getList("", UsersData.class);
    }

}

