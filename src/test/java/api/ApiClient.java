package api;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import models.User;
import static io.restassured.RestAssured.given;

public class ApiClient {
    private static final String BASE_URL = "https://qa-stellarburgers.education-services.ru";
    private static final String REGISTER_PATH = "/api/auth/register";
    private static final String USER_PATH = "/api/auth/user";
    @Step("Создание пользователя через API")
    public Response registerUser(User user) {
        return given()
                .header("Content-Type", "application/json")
                .body(user)
                .post(BASE_URL + REGISTER_PATH);
    }
    @Step("Удаление пользователя через API")
    public Response deleteUser(String token) {
        return given()
                .header("Content-Type", "application/json")
                .header("Authorization", token)
                .delete(BASE_URL + USER_PATH);
    }
    @Step("Получение токена из ответа")
    public String getAccessToken(Response response) {
        return response.jsonPath().getString("accessToken");
    }
}