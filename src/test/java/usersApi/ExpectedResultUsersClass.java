package usersApi;

public class ExpectedResultUsersClass {
    private String title;
    private String body;
    private Integer userId;
    private Integer id;

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public Integer getUserId() {
        return userId;
    }

    public Integer getId() {
        return id;
    }

    public ExpectedResultUsersClass(String title, String body, Integer userId, Integer id) {
        this.title = title;
        this.body = body;
        this.userId = userId;
        this.id = id;
    }
}
