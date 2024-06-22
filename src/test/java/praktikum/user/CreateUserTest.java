package praktikum.user;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Test;
import praktikum.TestConfig;
import praktikum.model.User;
import praktikum.model.response.ApiResponse;
import praktikum.model.response.UserResponse;
import praktikum.steps.UserSteps;

import static java.util.Objects.nonNull;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static praktikum.util.ApiRequestBuilder.createUser;
import static praktikum.util.ApiRequestBuilder.createUserWithoutRequiredField;
import static praktikum.util.ApiResponseMessage.*;

public class CreateUserTest extends TestConfig {

    private final UserSteps step = new UserSteps();
    private UserResponse userResponse;

    @Test
    @DisplayName("Создание уникального пользователя")
    public void createUserTest() {
        User user = createUser();
        userResponse = step
                .create(user)
                .statusCode(200)
                .body(SUCCESS, is(true))
                .extract().as(UserResponse.class);

        assertThat(userResponse.getUser(), equalTo(user));

    }

    @Test
    @DisplayName("Создание пользователя, который уже зарегистрирован")
    public void createExistUserTest() {
        User user = createUser();
        userResponse = step
                .create(user)
                .statusCode(200)
                .extract().as(UserResponse.class);

        ApiResponse response = step
                .create(user)
                .statusCode(403)
                .body(SUCCESS, is(false))
                .extract().as(ApiResponse.class);

        assertThat(response.getMessage(), equalTo(USER_ALREADY_EXIST));
    }

    @Test
    @DisplayName("Создание пользователя без заполнения поля email")
    public void createUserWithoutRequiredFieldTest() {
        User user = createUserWithoutRequiredField();
        ApiResponse response = step
                .create(user)
                .statusCode(403)
                .body(SUCCESS, is(false))
                .extract().as(ApiResponse.class);

        assertThat(response.getMessage(), equalTo(NOT_REQUIRED_FIELDS));
    }

    @After
    public void destroy() {
        if (nonNull(userResponse))
            step.delete(userResponse.getAccessToken());
    }
}
