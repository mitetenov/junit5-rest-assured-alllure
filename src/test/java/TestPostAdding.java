import io.restassured.RestAssured;
import io.restassured.filter.log.LogDetail;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.Is.is;

public class TestPostAdding {

    @BeforeAll
    public static void enableLoggingOfRequestAndResponseIfValidationFails() {
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails(LogDetail.ALL);
    }

    static String id;
    @Test
    @Order(1)
    public void postCreating(){
        id = given(Config.posts)
                .when()
                .body("{\n" +
                        "    \"title\": \"заголовок\",\n" +
                        "    \"body\": \"тело\",\n" +
                        "    \"userId\": \"1\"\n" +
                        "}")
                .post()
                .then()
                .assertThat()
                .statusCode(201)
                .assertThat()
                .body("title", is("заголовок"))
                .assertThat()
                .body("body", is("тело"))
                .assertThat()
                .body("userId", is("1"))
                .extract().body().path("id").toString();
    }

    @Test
    @Order(2)
    public void createdPostCheck(){
        given(Config.posts)
                .when()
                .get("/"+id)
                .then()
                .spec(Config.responseSpecification)
                .assertThat()
                .body("userId", is(1))
                .assertThat()
                .body("title", is("заголовок"))
                .assertThat()
                .body("body", is("тело"))
                .assertThat()
                .body("userId", is("1"))
                .assertThat()
                .body("id", is(id));
    }
}