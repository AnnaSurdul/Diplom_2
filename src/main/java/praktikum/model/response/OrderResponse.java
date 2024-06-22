package praktikum.model.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import praktikum.model.Order;

import java.util.List;

@Setter
@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderResponse {

    @JsonIgnore(value = false)
    private String name;
    private boolean success;
    private List<Order> orders;
    private int total;
    private int totalToday;
}
