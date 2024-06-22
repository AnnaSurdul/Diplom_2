package praktikum;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import org.junit.Before;

import static praktikum.config.ApiConfiguration.HOST;

public class TestConfig {

    @Before
    public void initRestAssured() {
        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .setBaseUri(HOST)
                .build();
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();
    }
}