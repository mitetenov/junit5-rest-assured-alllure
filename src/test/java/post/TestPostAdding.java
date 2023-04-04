package post;

import api.PostApi;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import models.Post;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TestPostAdding extends BasePostTest {

    @BeforeAll
    public static void enableLogger() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    @DisplayName("добавляем пост")
    @Description("добавляем пост и проверяем, что в ответе пришли все поля, заполненные нами + идентификатор поста")
    public void postCreating() {
        PostApi postApi = new PostApi();
        Post post = new Post(1, null, "заголовок", "тело");
        Response response = postApi.addPost(post);
        Post postFromResponse = response.getBody().as(Post.class);
        assertEquals(201, response.getStatusCode());
        assertAll(
                () -> assertNotNull(postFromResponse.getId(), "id не пустой"),
                () -> assertEquals(1, postFromResponse.getUserId(), "userId Не соответствует указанному при создании"),
                () -> assertEquals(post.getTitle(), postFromResponse.getTitle(), "title Не соответствует указанному при создании"),
                () -> assertEquals(post.getBody(), postFromResponse.getBody(), "body Не соответствует указанному при создании")
        );
    }
}