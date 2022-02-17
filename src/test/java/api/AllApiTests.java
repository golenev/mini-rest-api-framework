package api;

import models.posts.PostsData;
import models.usersInfo.UsersData;
import utils.StringUtils;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import java.util.List;
import java.util.stream.Collectors;
import static io.restassured.RestAssured.given;
import static utils.TestingConfiguration.*;
import static api.UtilApi.*;
public class AllApiTests {
Response response;

    @Test
    public void getAllPostsRequest() {
        //https://jsonplaceholder.typicode.com/posts
        //String url = getPostsUrl();
        response = useMethodGet(getPostsUrl());
        response.then().log().all();
        List<PostsData> posts = response.then().extract().body().jsonPath().getList("", PostsData.class);
        List<Integer> listOfPostsIds = posts.stream().map(x -> x.getId()).collect(Collectors.toList());
        List<Integer> sortedList = listOfPostsIds.stream().sorted().collect(Collectors.toList());
        for (int i = 0; i < listOfPostsIds.size(); i++) {
            Assert.assertEquals(listOfPostsIds.get(i), sortedList.get(i));
        }
        Assert.assertEquals("application/json; charset=utf-8", response.getContentType());
        Assert.assertEquals(response.getStatusCode(), 200);

        System.out.println("завершён шаг 1");
        System.out.println("начинается шаг 2");


        //https://jsonplaceholder.typicode.com/posts/99
        // String url = getPostsUrl(99);
        response = useMethodGet(getPostsUrl(99));
        response.then().log().all();
        Assert.assertEquals("application/json; charset=utf-8", response.getContentType());
        PostsData user = response.then().extract().body().jsonPath().getObject("", PostsData.class);
        Assert.assertEquals(user.getId(), 99);
        Assert.assertEquals(response.getStatusCode(), 200);
        Assert.assertEquals(user.getUserId(), 10);
        Assert.assertEquals(user.getId(), 99);
        Assert.assertNotEquals(user.getTitle(), "");
        Assert.assertNotEquals(user.getBody(), "");

        System.out.println("завершён шаг 2");
        System.out.println("начинается шаг 3");
        // @Test
        //https://jsonplaceholder.typicode.com/posts/150 404
        //public void getNotExistenceResponse() {

        response = useMethodGet(getPostsUrl(150));
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 404);

        System.out.println("завершён шаг 3");
        System.out.println("начинается шаг 4");
        // @Test
        //https://jsonplaceholder.typicode.com/posts/
        // public void postRequestToApi() {
        PostsData postsDataToSend = new PostsData(StringUtils.generateRandomString(), StringUtils.generateLastName(), 1, 101);
        response = useMethodPost(getPostsUrl(), postsDataToSend);
        Assert.assertEquals(response.getStatusCode(), 201);
        PostsData postsDataExpected = response
                .then().log().all()
                .extract().as(PostsData.class);
        Assert.assertEquals(postsDataExpected.getBody(), postsDataToSend.getBody());
        Assert.assertEquals(postsDataExpected.getTitle(), postsDataToSend.getTitle());

        System.out.println("завершён шаг 4");
        System.out.println("начинается шаг 5");


        // @Test
        // public void getAllUsersRequest() {
        //https://jsonplaceholder.typicode.com/users
        response = useMethodGet(getUsersUrl());
        response.then().log().all();
        List<UsersData> users = response.then().extract().body().jsonPath().getList("", UsersData.class);
        Assert.assertEquals(users.get(4).getAddress().getStreet(), "Skiles Walks");
        Assert.assertEquals(users.get(4).getAddress().getSuite(), "Suite 351");
        Assert.assertEquals(users.get(4).getAddress().getCity(), "Roscoeview");
        Assert.assertEquals(users.get(4).getAddress().getZipcode(), "33263");
        Assert.assertEquals(users.get(4).getAddress().getGeo().getLat(), "-31.8129");
        Assert.assertEquals(users.get(4).getAddress().getGeo().getLng(), "62.5342");
        Assert.assertEquals(users.get(4).getName(), "Chelsey Dietrich");
        Assert.assertEquals(users.get(4).getUsername(), "Kamren");
        Assert.assertEquals(users.get(4).getEmail(), "Lucio_Hettinger@annie.ca");
        Assert.assertEquals(users.get(4).getCompany().getBs(), "revolutionize end-to-end systems");
        System.out.println("завершён шаг 5");
        System.out.println("начинается шаг 6");


        //@Test
        //public void getRequiredUserRequest() {
        //https://jsonplaceholder.typicode.com/users/5
        response = useMethodGet(getUsersUrl(5));
        // UsersData user = response.then().extract().body().jsonPath().getObject("", UsersData.class);
        response.then().log().all();
        System.out.println("завершён шаг 6");

    }

}

