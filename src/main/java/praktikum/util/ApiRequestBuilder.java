package praktikum.util;

import com.github.javafaker.Faker;
import praktikum.model.Order;
import praktikum.model.User;

import java.util.List;

public class ApiRequestBuilder {

    private static final Faker faker = new Faker();

    public static User createUser() {
        return User.builder()
                .email(faker.bothify("???????##@gm??l.com"))
                .password(faker.bothify("?#?#???#???#??#"))
                .name(faker.funnyName().name())
                .build();
    }

    public static User createUserWithoutRequiredField() {
        return User.builder()
                .password(faker.bothify("?#?#???#???#??#"))
                .name(faker.funnyName().name())
                .build();
    }

    public static User incorrectUser() {
        return createUser();
    }

    public static User updateUser() {
        return createUser();
    }

    public static Order order() {
        return Order.builder()
                .ingredients(List.of("61c0c5a71d1f82001bdaaa6d", "61c0c5a71d1f82001bdaaa71"))
                .build();
    }

    public static Order emptyOrder() {
        return Order.builder()
                .ingredients(List.of())
                .build();
    }

    public static Order orderWithIncorrectIdIngredient() {
        return Order.builder()
                .ingredients(List.of("61c0c5a71d1f82001baftdaaa6", "61c0c5a71d1f820q5w01bdaaa1"))
                .build();
    }
}
