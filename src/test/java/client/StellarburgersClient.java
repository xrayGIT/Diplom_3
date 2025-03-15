package client;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import io.qameta.allure.model.Status;
import io.qameta.allure.restassured.AllureRestAssured;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import model.User;

import static io.restassured.RestAssured.given;
import static validations.Validations.checkStatus;

public class StellarburgersClient {
    private static final String REGISTER_USER_API = "/api/auth/register";
    private static final String USER_API = "/api/auth/user";
    private static final String LOGIN_USER_API = "/api/auth/login";
    private final RequestSpecification requestSpec;

    public StellarburgersClient(String BASE_URI) {
        requestSpec = given()
                .baseUri(BASE_URI)
                .header("Content-Type", "application/json");
    }

    // Создание пользователя POST https://stellarburgers.nomoreparties.site/api/auth/register
    @Step("Создание пользователя")
    public ValidatableResponse createUser(User user, int statusER) {
         ValidatableResponse response = given().filter(new AllureRestAssured())
                .spec(requestSpec)
                .body(user)
                .post(REGISTER_USER_API)
                .then();
        checkStatus(response, statusER);
        return response;
    }

    // Удаление пользователя DELETE https://stellarburgers.nomoreparties.site/api/auth/user
    @Step("Удаление пользователя")
    public ValidatableResponse deleteUser(String token, int statusER) {
        ValidatableResponse response = given().filter(new AllureRestAssured())
                .spec(requestSpec)
                .header("Authorization", token)
                .delete(USER_API)
                .then();
        checkStatus(response, statusER);
        return response;
    }

    @Step("Удаление пользователя")
    public ValidatableResponse deleteUser(User user, int statusER) {
        ValidatableResponse response = null;
        String token = loginUser(user, 200).extract().body()
                .jsonPath()
                .get("accessToken");
        if (token != null) {
            response = deleteUser(token, statusER);
        } else {
            Allure.step("Удаление пользователя не возможно. Токен отсутствует, проверьте был ли он сгенерирован на предыдущих шагах", Status.BROKEN);
        }
        return response;
    }

    // Логин пользователя POST https://stellarburgers.nomoreparties.site/api/auth/login
    @Step("Логин пользователя")
    public ValidatableResponse loginUser(User user, int statusER) {
        ValidatableResponse response =  given().filter(new AllureRestAssured())
                .spec(requestSpec)
                .body(user)
                .post(LOGIN_USER_API)
                .then();
        checkStatus(response, statusER);
        return response;
    }
}
