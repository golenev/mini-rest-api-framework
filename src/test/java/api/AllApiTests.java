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

    private Response responseGet;
    private Response responsePost;

    @BeforeMethod



    @Test
    public void getAllPostsRequest() {
        //https://jsonplaceholder.typicode.com/posts
        String url = getPostsUrl();
        Response response = useMethodGet(url);
        response.then().log().all();
        List<PostsData> posts = response.then().extract().body().jsonPath().getList("", PostsData.class);
        List<Integer> listOfPostsIds = posts.stream().map(x->x.getId()).collect(Collectors.toList());
        List<Integer> sortedList = listOfPostsIds.stream().sorted().collect(Collectors.toList());
               for (int i = 0; i < listOfPostsIds.size(); i++) {
                   Assert.assertEquals(listOfPostsIds.get(i) , sortedList.get(i));
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
        PostsData postsDataToSend = new PostsData(StringUtils.generateRandomString(), StringUtils.generateLastName(), 1, 101);
        Response response = useMethodPost(getPostsUrl(), postsDataToSend);
        Assert.assertEquals(response.getStatusCode(), 201);
        PostsData postsDataExpected = response
                .then().log().all()
                .extract().as( PostsData.class);
        Assert.assertEquals(postsDataExpected.getBody(), postsDataToSend.getBody());
        Assert.assertEquals(postsDataExpected.getTitle(), postsDataToSend.getTitle());
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

