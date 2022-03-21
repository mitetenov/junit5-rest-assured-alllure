import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class Config {
    static String BaseUri = "https://jsonplaceholder.typicode.com";
    static String postsApiUrl = "/posts";

    public static RequestSpecification posts = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .setAccept(ContentType.JSON)
            .setBaseUri(BaseUri)
            .setBasePath(postsApiUrl)
            .build();

    public static ResponseSpecification responseSpecification = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .build();
}

