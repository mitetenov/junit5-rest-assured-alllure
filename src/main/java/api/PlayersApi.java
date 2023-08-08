package api;

import defaultSpecification.DefaultRequestSpecification;
import io.restassured.response.Response;
import models.PlayerRequestDTO;
import models.PlayerRequestOneDTO;

import static io.restassured.RestAssured.given;

public class PlayersApi {

    public Response create(PlayerRequestDTO playerRequestDTO, String token) {
        return given(DefaultRequestSpecification.defaultRequestSpec)
                .auth()
                .oauth2(token)
                .body(playerRequestDTO)
                .post("/automationTask/create");

    }

    public Response getOne(PlayerRequestOneDTO player, String token) {
        return given(DefaultRequestSpecification.defaultRequestSpec)
                .auth()
                .oauth2(token)
                .body(player)
                .post("/automationTask/getOne");
    }

    public Response getAll(String token) {
        return given(DefaultRequestSpecification.defaultRequestSpec)
                .auth()
                .oauth2(token)
                .get("/automationTask/getAll");
    }

    public Response deleteOne(String id, String token) {
        return given(DefaultRequestSpecification.defaultRequestSpec)
                .auth()
                .oauth2(token)
                .delete("automationTask/deleteOne/" + id);
    }
}
