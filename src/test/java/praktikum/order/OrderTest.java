package praktikum.order;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import praktikum.TestConfig;
import praktikum.model.response.ApiResponse;
import praktikum.model.response.UserResponse;
import praktikum.steps.OrderSteps;
import praktikum.steps.UserSteps;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static java.util.Objects.nonNull;
import static org.hamcrest.Matchers.is;
import static praktikum.util.ApiRequestBuilder.*;
import static praktikum.util.ApiResponseMessage.*;

public class OrderTest extends TestConfig {

    private final OrderSteps orderStep = new OrderSteps();
    private final UserSteps userStep = new UserSteps();
    private UserResponse userResponse;

    @Before
    public void init() {
        userResponse = userStep.create(createUser())
                .extract().as(UserResponse.class);
    }

    @Test
    @DisplayName("Создание нового заказа с ингредиентами с авторизацией")
    public void createOrderTest() {
        orderStep
                .create(order(), userResponse.getAccessToken())
                .statusCode(200)
                .body(SUCCESS, is(true));
    }

    @Test
    @DisplayName("Получить заказы конкретного пользователя с авторизацией")
    public void getForUserOrdersTest() {
        orderStep.getForUser(userResponse.getAccessToken())
                .statusCode(200)
                .body(SUCCESS, is(true));
    }

    @Test
    @DisplayName("Создание нового заказа без ингридиентов с авторизацией")
    public void createEmptyOrderTest() {
        ApiResponse response = orderStep
                .create(emptyOrder(), userResponse.getAccessToken())
                .statusCode(400)
                .body(SUCCESS, is(false))
                .extract().as(ApiResponse.class);

        assertThat(response.getMessage(), equalTo(RESPONSE_FOR_EMPTY_ORDER));
    }

    @Test
    @DisplayName("Создание нового заказа с неверным хешем ингредиентов")
    public void createOrderWithIncorrectIdIngredientTest() {
        orderStep
                .create(orderWithIncorrectIdIngredient(), userResponse.getAccessToken())
                .statusCode(500);
    }

    @Test
    @DisplayName("Создание заказа неавторизованным пользователем")
    public void createOrderWithUnAuthorizedUserTest() {
        //TODO API позволяет создать заказ без токена авторизации,в Документации данный кейс не описсан
        orderStep
                .createWithoutToken(order())
                .statusCode(200)
                .body(SUCCESS, is(true));
    }

    @Test
    @DisplayName("Получить заказы конкретного пользователя без авторизации")
    public void getForUnauthorizedUserOrdersTest() {
        ApiResponse response = orderStep.getWithoutToken()
                .statusCode(401)
                .body(SUCCESS, is(false))
                .extract().as(ApiResponse.class);

        assertThat(response.getMessage(), equalTo(UNAUTHORIZED));
    }

    @After
    public void destroy() {
        if (nonNull(userResponse))
            userStep.delete(userResponse.getAccessToken());
    }
}
