package api;

import defaultSpecification.DefaultRequestSpecification;
import io.restassured.response.Response;
import models.CredentialsDTO;

import static io.restassured.RestAssured.given;

public class AuthenticationApi {

    public Response login(CredentialsDTO credentialsDTO) {
        return given(DefaultRequestSpecification.defaultRequestSpec)
                .body(credentialsDTO)
                .post("/tester/login");
    }

}
