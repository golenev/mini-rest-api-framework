package usersApi;

public class PostsData {
    private String title;
    private String body;
    private int userId;
    private int id;

    public PostsData(String title, String body, Integer userId, int id) {
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
}

