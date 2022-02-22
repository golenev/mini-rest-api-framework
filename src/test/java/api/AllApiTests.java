package api;

import models.posts.PostsData;
import models.users.User;
import utils.ListUtils;
import utils.StringUtils;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;
import utils.TestingConfiguration;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import static io.restassured.RestAssured.given;
import static utils.TestingConfiguration.*;
import static api.UtilApi.*;

public class AllApiTests {

Response response;

    @Test
    public void executeAllRequests() {
        response = useMethodGet(getPostsUrl());
        response.then().log().all();
        List<PostsData> postsFromServer = ListUtils.parseJsonToList(response,"", PostsData.class);
        List<PostsData> postsSorted = postsFromServer.stream().sorted(Comparator.comparingLong(PostsData::getId)).collect(Collectors.toList());
        Assert.assertEquals(postsFromServer, postsSorted, "sorry, fields of your object are not equals");
        Assert.assertEquals(ContentType.JSON.getOption(), response.getContentType());
        Assert.assertEquals(response.getStatusCode(), StatusCode.OK.getOption(), "sorry, your status code does not match");

        response = useMethodGet(getPostsUrl(getTestDataAsLong("expectedId")));
        response.then().log().all();
        Assert.assertEquals(ContentType.JSON.getOption(), response.getContentType());

        PostsData user = ListUtils.parseJsonToObject(response, "", PostsData.class);
        Assert.assertEquals(response.getStatusCode(), StatusCode.OK.getOption(), "sorry, your status code does not match");
        Assert.assertEquals(user.getUserId(), getTestDataAsLong("expectedUserId"), "sorry, your UserIds does not match");
        Assert.assertEquals(user.getId(), getTestDataAsLong("expectedId"), "sorry, Ids does not match");
        Assert.assertNotNull(user.getTitle(),"sorry, your title is null");
        Assert.assertNotNull(user.getBody(),"sorry, your body is null");

        response = useMethodGet(getPostsUrl(getTestDataAsLong("requiredPostsId")));
        response.then().log().all();
        Assert.assertEquals(response.getStatusCode(), StatusCode.NOT_FOUND.getOption(), "sorry, your status code does not match");

        PostsData postsDataToSend = new PostsData(StringUtils.insertBodyText(), StringUtils.insertTitleText(),
               getTestDataAsLong("fieldUserIdFromConstructor"),
                getTestDataAsLong("fieldIdFromConstructor"));
        response = useMethodPost(getPostsUrl(), postsDataToSend);
        Assert.assertEquals(response.getStatusCode(), StatusCode.CREATED.getOption(), "sorry, your status code does not match");
        PostsData postsDataExpected = response
                .then().log().all()
                .extract().as(PostsData.class);
        Assert.assertEquals(postsDataExpected.getBody(), postsDataToSend.getBody(), "sorry, your posts does not match");
        Assert.assertEquals(postsDataExpected.getTitle(), postsDataToSend.getTitle(), "sorry, your posts titles does not match");

        response = useMethodGet(getUsersUrl());
        response.then().log().all();
        List<User> users = ListUtils.parseJsonToList(response, "", User.class);
        User actualUser = users.stream().filter(u -> u.getId()==getTestDataAsLong("requiredUserId")).findFirst().get();
        Assert.assertEquals(actualUser, getUserFromResources(), "sorry, your users does not match");

        response = useMethodGet(getUsersUrl((int) getTestDataAsLong("requiredUserId")));
        User userFromServer = ListUtils.parseJsonToObject(response,"", User.class);
        response.then().log().all();
        Assert.assertEquals(userFromServer, getUserFromResources(), "sorry, your users does not match");

        }

}

