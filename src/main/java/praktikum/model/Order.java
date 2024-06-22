package praktikum.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;

import java.util.List;

@Getter
@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Order {

    private String id;
    private String status;
    private int number;
    private String createdAt;
    private String updatedAt;

    private List<String> ingredients;
}
