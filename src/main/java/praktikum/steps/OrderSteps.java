package praktikum.steps;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import praktikum.model.Order;

import static io.restassured.RestAssured.given;
import static praktikum.config.ApiConfiguration.ORDERS;

public class OrderSteps {
@Step("Direct request POST /api/orders")
    public ValidatableResponse create(Order order, String accessToken) {
        return given()
                .header("Authorization", accessToken)
                .body(order)
                .when()
                .post(ORDERS)
                .then();
    }
    @Step("Direct request GET /api/orders")
    public ValidatableResponse getForUser(String accessToken) {
        return given()
                .header("Authorization", accessToken)
                .when()
                .get(ORDERS)
                .then();
    }
    @Step("Direct request POST /api/orders")
    public ValidatableResponse createWithoutToken(Order order) {
        return given()
                .body(order)
                .when()
                .post(ORDERS)
                .then();
    }
    @Step("Direct request GET /api/orders")
    public ValidatableResponse getWithoutToken() {
        return given()
                .when()
                .get(ORDERS)
                .then();
    }
}
