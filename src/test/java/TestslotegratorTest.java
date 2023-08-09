import api.AuthenticationApi;
import api.PlayersApi;
import configs.Common;
import io.restassured.response.Response;
import models.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)

public class TestslotegratorTest extends BaseTest {
    static List<PlayerResponseDTO_id> listOfCreatedUsers = new ArrayList<>();

    @Test
    @DisplayName("auth")
    @Order(1)
    public void auth() {
        AuthenticationApi authenticationApi = new AuthenticationApi();
        CredentialsDTO credentialsDTO = new CredentialsDTO(Common.LOGIN, Common.PASSWORD);
        Response response = authenticationApi.login(credentialsDTO);
        assertEquals(200, response.getStatusCode()); //failed because in expected part of task wrote,
        // that expected is 200, but actual is 201
    }

    @Test
    @DisplayName("add Players")
    @Order(2)
    public void addPlayers() {
        for (int i = 0; i < 12; i++) {
            String name = RandomStringUtils.randomAlphabetic(7);
            String username = RandomStringUtils.randomAlphabetic(7);
            String surname = RandomStringUtils.randomAlphabetic(7);
            String currency_code = "EUR";
            String email = RandomStringUtils.randomAlphabetic(5) + "@" + RandomStringUtils.randomAlphabetic(5)
                    + ".com";
            String password_change = RandomStringUtils.randomAlphabetic(8);
            new PlayerRequestDTO();
            PlayerRequestDTO player = PlayerRequestDTO.builder()
                    .name(name)
                    .username(username)
                    .surname(surname)
                    .currency_code(currency_code)
                    .email(email)
                    .password_change(password_change)
                    .password_repeat(password_change)
                    .build();
            PlayersApi playersApi = new PlayersApi();
            Response response = playersApi.create(player, token);
            assertEquals(201, response.getStatusCode());
            listOfCreatedUsers.add(response.getBody().as(PlayerResponseDTO_id.class));
        }
    }

    @Test
    @DisplayName("added Players Getting")
    @Order(3)
    public void addedPlayersGetting() {
        PlayersApi playersApi = new PlayersApi();
        for (PlayerResponseDTO_id playerResponseDTO : listOfCreatedUsers) {
            PlayerRequestOneDTO player = new PlayerRequestOneDTO();
            player.setEmail(playerResponseDTO.getEmail());
            Response response = playersApi.getOne(player, token);
            assertEquals(200, response.getStatusCode()); //failed because in expected part of task wrote,
            // that expected is 200, but actual is 201
        }
    }

    @Test
    @DisplayName("players Sorting")
    @Order(4)
    public void playersSorting() {
        PlayersApi playersApi = new PlayersApi();
        Response response = playersApi.getAll(token);
        List<PlayerResponseDTO> allPlayers = Arrays.asList(response.getBody().as(PlayerResponseDTO[].class));
        allPlayers.sort(Comparator.comparing(PlayerResponseDTO::getName));
    }

    @Test
    @DisplayName("players Deleting")
    @Order(5)
    public void playersDeleting() {
        PlayersApi playersApi = new PlayersApi();
        Response getAllResponse = playersApi.getAll(token);
        PlayerResponseDTO[] allPlayers = getAllResponse.getBody().as(PlayerResponseDTO[].class);
        for (PlayerResponseDTO player : allPlayers) {
            Response deleteResponse = playersApi.deleteOne(player.getId(), token);
            assertEquals(200, deleteResponse.getStatusCode());
        }
    }

    @Test
    @DisplayName("checking Is List Of Players Is Empty")
    @Order(6)
    public void checkingIsListOfPlayersIsEmpty() {
        PlayersApi playersApi = new PlayersApi();
        Response response = playersApi.getAll(token);
        assertEquals(200, response.getStatusCode());
        List<PlayerResponseDTO> allPlayers = Arrays.asList(response.getBody().as(PlayerResponseDTO[].class));
        assertTrue(allPlayers.isEmpty(), "list of all players is not empty");
    }
}
