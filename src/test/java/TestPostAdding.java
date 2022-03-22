import io.qameta.allure.Description;
import io.restassured.RestAssured;
import models.Post;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import com.google.gson.Gson;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

public class TestPostAdding {
    Post post = new Post(1, "заголовок", "тело",null);
    static int id; //разные тестовые методы junit запускаются в разных тредах и, следовательно, работают с
    // разными экземплярами объектов. может и есть способ как-то красиво передать значение, но я его не знаю
    @BeforeAll
    public static void enableLogger() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    @DisplayName("добавляем пост")
    @Description("добавляем пост и проверяем, что в ответе пришли все поля, заполненные нами + идентификатор поста")
    @Order(1)
    public void postCreating(){
        Gson gson = new Gson();
        id = given(DefaultRequestSpecs.defaultRequestSpec)
                .when()
                .body(gson.toJson(post))
                .post()
                .then()
                .assertThat()
                .statusCode(201)
                .assertThat()
                .body("title", is(post.getTitle()))
                .assertThat()
                .body("body", is(post.getBody()))
                .assertThat()
                .body("userId", is(post.getUserId()))
                .extract().body().path("id");
    }

    @Test
    @DisplayName("проверяем пост")
    @Description("проверяем, что добавленный пост можно получить")
    @Order(2)
    public void createdPostCheck(){
        post.setId(id);
        given(DefaultRequestSpecs.defaultRequestSpec)
                .when()
                .get("/" + post.getId())
                .then()
                .spec(DefaultResponseSpecs.defaultResponseSpecification)
                .assertThat()
                .body("userId", is(post.getUserId()))
                .assertThat()
                .body("title", is(post.getTitle()))
                .assertThat()
                .body("body", is(post.getBody()))
                .assertThat()
                .body("userId", is(post.getUserId()))
                .assertThat()
                .body("id", is(post.getId()));
    }
}