package models;

public class Post {
    private int userId;
    private String title;
    private String body;
    private Integer id;

    public Post(int userId, String title, String body, Integer id){
        this.userId = userId;
        this.title = title;
        this.body = body;
        this.id = id;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}
