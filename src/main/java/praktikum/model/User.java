package praktikum.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@Getter
@Builder
@EqualsAndHashCode(exclude = {"password"})
@JsonInclude(JsonInclude.Include.NON_NULL)
@NoArgsConstructor
@AllArgsConstructor
public class User {

    private String email;
    private String password;
    private String name;
}
