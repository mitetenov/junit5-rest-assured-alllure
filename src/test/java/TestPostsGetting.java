import io.qameta.allure.Description;
import io.restassured.RestAssured;
import org.junit.jupiter.api.*;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestPostsGetting {
    @BeforeAll
    public static void enableLogger() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }

    @Test
    @DisplayName("проверяем длинну коллекции")
    @Description("в запросе должно приходить 100 постов")
    public void checkCollectionLength() {
        given(DefaultRequestSpecs.defaultRequestSpec)
                .when()
                .get()
                .then()
                .spec(DefaultResponseSpecs.defaultResponseSpecification)
                .assertThat()
                .body("size()", is(100));
    }

    @Test
    @DisplayName("проверяем, что в ответе приходят все поля")
    @Description("должно прийти 4 поля: userId, id, title и body")
    public void checkField() {
        given(DefaultRequestSpecs.defaultRequestSpec)
                .when()
                .get()
                .then()
                .spec(DefaultResponseSpecs.defaultResponseSpecification)
                .assertThat()
                .body("[0].userId", is(1))
                .assertThat()
                .body("[0].id", is(1))
                .assertThat()
                .body("[0].title", is("sunt aut facere repellat provident occaecati excepturi optio reprehenderit"))
                .assertThat()
                .body("[0].body", is("quia et suscipit\nsuscipit recusandae consequuntur expedita et cum\nreprehenderit molestiae ut ut quas totam\nnostrum rerum est autem sunt rem eveniet architecto"));
    }
}