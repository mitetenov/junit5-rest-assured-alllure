package post;

import common.BaseTest;
import configs.Common;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class BasePostTest extends BaseTest {
    public static RequestSpecification defaultRequestSpec = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .setAccept(ContentType.JSON)
            .setBaseUri(Common.BaseUri)
            .setBasePath(Common.POSTS)
            .addFilter(new AllureRestAssured())
            .build();

    public static ResponseSpecification defaultResponseSpecification = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .build();
}
