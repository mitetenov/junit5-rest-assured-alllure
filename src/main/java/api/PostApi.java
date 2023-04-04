package api;

import defaultSpecification.DefaultRequestSpecification;
import io.restassured.response.Response;
import models.Post;

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
}
