package praktikum.model.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import praktikum.model.User;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateUserResponse {

    private boolean success;
    private User user;
}
