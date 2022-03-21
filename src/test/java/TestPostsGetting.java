import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class TestPostsGetting {
    @BeforeAll
    public static void enableLoggingOfRequestAndResponseIfValidationFails() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL);
    }

    @Test
    public void checkCollectionLength() {
        given(Config.posts)
                .when()
                .get()
                .then()
                .spec(Config.responseSpecification)
                .assertThat()
                .body("size()", is(100));
    }
    @Test
    public void checkField() {
        given(Config.posts)
                .when()
                .get()
                .then()
                .spec(Config.responseSpecification)
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
