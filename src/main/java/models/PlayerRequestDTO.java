package models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public class PlayerRequestDTO {
    @JsonProperty("currency_code")
    private String currency_code;

    @JsonProperty("email")
    private String email;

    @JsonProperty("name")
    private String name;

    @JsonProperty("password_change")
    private String password_change;

    @JsonProperty("password_repeat")
    private String password_repeat;

    @JsonProperty("surname")
    private String surname;

    @JsonProperty("username")
    private String username;
}
