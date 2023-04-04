package api;

import defaultSpecification.DefaultRequestSpecification;
import io.qameta.allure.internal.shadowed.jackson.core.type.TypeReference;
import io.qameta.allure.internal.shadowed.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import lombok.SneakyThrows;
import models.Post;

import java.io.InputStream;
import java.util.List;

import static io.restassured.RestAssured.given;

public class PostApi {

    public Response getPostById(Integer id) {
        return given(DefaultRequestSpecification.defaultRequestSpec)
                .get("/" + id);
    }

    public Response addPost(Post post) {
        return given(DefaultRequestSpecification.defaultRequestSpec)
                .body(post)
                .post();
    }

    public Response getAllPosts() {
        return given(DefaultRequestSpecification.defaultRequestSpec)
                .get();
    }
    @SneakyThrows
    public List<Post> getPosts(InputStream posts) {
        ObjectMapper objectMapper = new ObjectMapper();
            TypeReference<List<Post>> typeReference = new TypeReference<>() {};
            return objectMapper.readValue(posts, typeReference);
    }
}
