package post;

import api.PostApi;
import io.qameta.allure.Description;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import lombok.SneakyThrows;
import models.Post;
import org.junit.jupiter.api.*;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestPostsGetting extends BasePostTest {
    @BeforeAll
    public static void enableLogger() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    @DisplayName("проверяем длину коллекции")
    @Description("в запросе должно приходить 100 постов")
    @SneakyThrows
    public void checkCollectionLength() {
        PostApi postApi = new PostApi();
        Response response = postApi.getAllPosts();
        List<Post> posts = Arrays.asList(response.getBody().as(Post[].class));
        assertEquals(200, response.getStatusCode());
        for (Post post : posts) {
            assertNotNull(post.getBody(), "поле body пустое у одного из постов");
            assertNotNull(post.getId(), "поле id пустое у одного из постов");
            assertNotNull(post.getTitle(), "поле title пустое у одного из постов");
            assertNotNull(post.getUserId(), "поле userId пустое у одного из постов");
        }
    }

    @Test
    @DisplayName("проверяем пост")
    @Description("проверяем, что добавленный пост можно получить")
    public void createdPostCheck() {
        PostApi postApi = new PostApi();
        Post post = new Post(1, null, "заголовок", "тело");
        Post postAfterCreating = postApi.addPost(post).as(Post.class);
        Response response = postApi.getPostById(postAfterCreating.getId());
        Post postFromResponse = response.getBody().as(Post.class);
        assertEquals(200, response.getStatusCode());
        assertAll(
                () -> assertEquals(post.getId(), postFromResponse.getId(), "id не соответствует запрошенному"),
                () -> assertEquals(post.getUserId(), postFromResponse.getUserId(), "userId Не соответствует запрошенному"),
                () -> assertEquals(post.getTitle(), postFromResponse.getTitle(), "title Не соответствует запрошенному"),
                () -> assertEquals(post.getBody(), postFromResponse.getBody(), "body Не соответствует запрошенному")
        );
    }
}