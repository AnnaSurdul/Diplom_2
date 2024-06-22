package praktikum.steps;

import io.qameta.allure.Step;
import io.restassured.response.ValidatableResponse;
import praktikum.model.User;

import static io.restassured.RestAssured.given;
import static praktikum.config.ApiConfiguration.*;

public class UserSteps {
    @Step("Direct request POST /api/auth/register")
    public ValidatableResponse create(User user) {
        return given()
                .body(user)
                .when()
                .post(CREATE_USER)
                .then();
    }
    @Step("Direct request POST /api/auth/login")
    public ValidatableResponse login(User user) {
        return given()
                .body(user)
                .when()
                .post(LOGIN)
                .then();
    }
    @Step("Direct request PATCH /api/auth/user")
    public ValidatableResponse update(String accessToken, User user) {
        return given()
                .header(AUTHORIZATION, accessToken)
                .body(user)
                .when()
                .patch(USER)
                .then();
    }
    @Step("Direct request PATCH /api/auth/user")
    public ValidatableResponse updateWithoutToken(User user) {
        return given()
                .body(user)
                .when()
                .patch(USER)
                .then();
    }
    @Step("Direct request DELETE /api/auth/user")
    public ValidatableResponse delete(String accessToken) {
        return given()
                .header(AUTHORIZATION, accessToken)
                .when()
                .delete(USER)
                .then();
    }
}