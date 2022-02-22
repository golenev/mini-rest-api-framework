package models.posts;

import utils.StringUtils;
import utils.TestingConfiguration;

public class PostsData {
    private String title;
    private String body;
    private long userId;
    private long id;

    public PostsData(String title, String body, long userId, long id) {
        this.title = title;
        this.body = body;
        this.userId = userId;
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public long getUserId() {
        return userId;
    }

    public long getId() {
        return id;
    }

    @Override
    public String toString() {
        return "PostsData{" +
                "title='" + title + '\'' +
                ", body='" + body + '\'' +
                ", userId=" + userId +
                ", id=" + id +
                '}';
    }

}

