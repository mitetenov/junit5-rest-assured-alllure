import Configs.Common;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class DefaultRequestSpecs {
    public static RequestSpecification defaultRequestSpec = new RequestSpecBuilder()
            .setContentType(ContentType.JSON)
            .setAccept(ContentType.JSON)
            .setBaseUri(Common.BaseUri)
            .setBasePath(Common.postsApiUrl)
            .addFilter(new AllureRestAssured())
            .build();
}
