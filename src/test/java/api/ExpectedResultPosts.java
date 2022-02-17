package api;

public class ExpectedResultPosts {
    private String title;
    private String body;
    private int userId;
    private int id;

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

    public ExpectedResultPosts(String title, String body, int userId, int id) {
        this.title = title;
        this.body = body;
        this.userId = userId;
        this.id = id;
    }
}
