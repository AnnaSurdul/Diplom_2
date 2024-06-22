package praktikum.user;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import praktikum.TestConfig;
import praktikum.model.User;
import praktikum.model.response.ApiResponse;
import praktikum.model.response.UpdateUserResponse;
import praktikum.model.response.UserResponse;
import praktikum.steps.UserSteps;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static praktikum.util.ApiRequestBuilder.createUser;
import static praktikum.util.ApiRequestBuilder.updateUser;
import static praktikum.util.ApiResponseMessage.SUCCESS;
import static praktikum.util.ApiResponseMessage.UNAUTHORIZED;

public class UpdateUserTest extends TestConfig {

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
    @DisplayName("Изменение данных авторизованного пользователя")
    public void updateUserTest() {
        user = updateUser();
        UpdateUserResponse response = step
                .update(userResponse.getAccessToken(), user)
                .statusCode(200)
                .body(SUCCESS, is(true))
                .extract().as(UpdateUserResponse.class);

        assertThat(response.getUser(), equalTo(user));
    }

    @Test
    @DisplayName("Изменение данных не авторизованного пользователя")
    public void updateUnauthorizedUserTest() {
        user = updateUser();
        ApiResponse response = step
                .updateWithoutToken(user)
                .statusCode(401)
                .body(SUCCESS, is(false))
                .extract().as(ApiResponse.class);

        assertThat(response.getMessage(), equalTo(UNAUTHORIZED));

    }

    @After
    public void destroy() {
        step.delete(userResponse.getAccessToken());
    }
}
