package praktikum.user;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import praktikum.TestConfig;
import praktikum.model.User;
import praktikum.model.response.ApiResponse;
import praktikum.model.response.UserResponse;
import praktikum.steps.UserSteps;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static praktikum.util.ApiRequestBuilder.createUser;
import static praktikum.util.ApiRequestBuilder.incorrectUser;
import static praktikum.util.ApiResponseMessage.INCORRECT_LOGIN;
import static praktikum.util.ApiResponseMessage.SUCCESS;

public class LoginUserTest extends TestConfig {

    private final UserSteps step = new UserSteps();
    private User user;
    private UserResponse userResponse;

    @Before
    public void init() {
        user = createUser();
        userResponse = step
                .create(user)
                .extract().as(UserResponse.class);
    }

    @Test
    @DisplayName("Логин под существующим пользователем")
    public void loginExistingUserTest() {
        step
                .login(user)
                .statusCode(200)
                .body(SUCCESS, is(true))
                .extract().as(UserResponse.class);

        assertThat(userResponse.getUser(), equalTo(user));
    }

    @Test
    @DisplayName("Логин с неверным логином и паролем")
    public void incorrectLoginTest() {
        ApiResponse response = step
                .login(incorrectUser())
                .statusCode(401)
                .body(SUCCESS, is(false))
                .extract().as(ApiResponse.class);

        assertThat(response.getMessage(), equalTo(INCORRECT_LOGIN));
    }

    @After
    public void destroy() {
        step.delete(userResponse.getAccessToken());
    }
}
