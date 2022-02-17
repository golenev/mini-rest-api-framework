package api;

import models.posts.PostsData;
import models.users.User;
import utils.StringUtils;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.TestingConfiguration;
import java.util.List;
import java.util.stream.Collectors;
import static io.restassured.RestAssured.given;
import static utils.TestingConfiguration.*;
import static api.UtilApi.*;
public class AllApiTests {

Response response;

    @Test
    public void getAllPostsRequest() {
        response = useMethodGet(getPostsUrl());
        response.then().log().all();
        List<PostsData> posts = response.then().extract().body().jsonPath().getList("", PostsData.class);
        List<Integer> listOfPostsIds = posts.stream().map(x -> x.getId()).collect(Collectors.toList());
        List<Integer> sortedList = listOfPostsIds.stream().sorted().collect(Collectors.toList());
        for (int i = 0; i < listOfPostsIds.size(); i++) {
            Assert.assertEquals(listOfPostsIds.get(i), sortedList.get(i));
        }
        Assert.assertEquals(getContentTypeFromTestingData(), response.getContentType());
        Assert.assertEquals(response.getStatusCode(), 200, "sorry, your status code does not match");

        response = useMethodGet(getPostsUrl(getExpectedIdFromTestingData()));
        response.then().log().all();
        Assert.assertEquals(getContentTypeFromTestingData(), response.getContentType());
        PostsData user = response.then().extract().body().jsonPath().getObject("", PostsData.class);
        Assert.assertEquals(response.getStatusCode(), 200, "sorry, your status code does not match");
        Assert.assertEquals(user.getUserId(), getExpectedUserIdFromTestingData());
        Assert.assertEquals(user.getId(), getExpectedIdFromTestingData());
        Assert.assertNotEquals(user.getTitle(), "");
        Assert.assertNotEquals(user.getBody(), "");

        response = useMethodGet(getPostsUrl(getRequiredPostsIdFromTestingData()));
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), 404, "sorry, your status code does not match");

        PostsData postsDataToSend = new PostsData(StringUtils.insertBodyText(), StringUtils.insertTitleText(),
                TestingConfiguration.getFieldUserIdForConstructorTestingData(),
                TestingConfiguration.getFieldIdForConstructorTestingData());
        response = useMethodPost(getPostsUrl(), postsDataToSend);
        Assert.assertEquals(response.getStatusCode(), 201, "sorry, your status code does not match");
        PostsData postsDataExpected = response
                .then().log().all()
                .extract().as(PostsData.class);
        Assert.assertEquals(postsDataExpected.getBody(), postsDataToSend.getBody());
        Assert.assertEquals(postsDataExpected.getTitle(), postsDataToSend.getTitle());

        response = useMethodGet(getUsersUrl());
        response.then().log().all();
        List<User> users = response.then().extract().body().jsonPath().getList("", User.class);
        User actualUser = users.stream().filter(u -> u.getId()==getRequiredUserIdFromTestingData()).findFirst().get();
        Assert.assertEquals(actualUser, User.createExpectedUser());

        response = useMethodGet(getUsersUrl(getRequiredUserIdFromTestingData()));
        User anyUser = response.then().extract().body().jsonPath().getObject("", User.class);
        response.then().log().all();
        Assert.assertEquals(anyUser, actualUser);
        }

}

