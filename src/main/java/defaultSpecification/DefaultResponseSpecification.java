package defaultSpecification;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.ResponseSpecification;

public class DefaultResponseSpecification {
    public static ResponseSpecification defaultResponseSpecification = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .build();
}