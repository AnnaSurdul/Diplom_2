package praktikum.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import praktikum.model.User;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserResponse {

    private boolean success;
    private User user;
    private String accessToken;
    private String refreshToken;
}