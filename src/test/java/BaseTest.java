import api.AuthenticationApi;
import configs.Common;
import models.CredentialsDTO;
import models.TokenDTO;
import org.junit.jupiter.api.BeforeAll;

public abstract class BaseTest {
    protected static String token;
    @BeforeAll
    public static void setToken() {
        AuthenticationApi authenticationApi = new AuthenticationApi();
        if (token == null) {
            token = authenticationApi.login(new CredentialsDTO(Common.LOGIN, Common.PASSWORD))
                    .getBody().as(TokenDTO.class).getAccess_token();
        }
    }
}
