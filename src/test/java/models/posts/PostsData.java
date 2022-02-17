package models.posts;

import utils.StringUtils;
import utils.TestingConfiguration;

public class PostsData {
    private String title;
    private String body;
    private int userId;
    private int id;

    public PostsData(String title, String body, int userId, int id) {
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

    public int getUserId() {
        return userId;
    }

    public int getId() {
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

