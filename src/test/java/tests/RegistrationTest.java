package tests;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.RegisterPage;
import utils.UserGenerator;
import static org.junit.jupiter.api.Assertions.assertTrue;
import api.ApiClient;
import models.User;
import org.junit.jupiter.api.AfterEach;

public class RegistrationTest extends BaseTest {
    private MainPage mainPage;
    private LoginPage loginPage;
    private RegisterPage registerPage;
    private User user;
    private String accessToken;
    private ApiClient apiClient;
    @BeforeEach
    @Step("Подготовка к тесту регистрации")
    public void init() {
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        apiClient = new ApiClient();
        waitForPageStable();
    }
    @AfterEach
    @Step("Очистка после теста: удаление пользователя и закрытие браузера")
    public void cleanUp() {
        if (accessToken != null) {
            apiClient.deleteUser(accessToken);
        }
        if (driver != null) {
            driver.quit();
        }
    }
    @Test
    @DisplayName("Успешная регистрация")
    @Description("Проверка регистрации с корректными данными")
    public void successfulRegistrationTest() {
        user = UserGenerator.generateValidUser();
        mainPage.clickLoginButton();
        loginPage.clickRegisterLink();
        registerPage.register(user.getName(), user.getEmail(), user.getPassword());
        assertTrue(registerPage.isRegistrationSuccess(), "Регистрация завершилась не успешно");

        var response = apiClient.registerUser(user);
        accessToken = apiClient.getAccessToken(response);
    }
    @Test
    @DisplayName("Ошибка при пароле меньше 6 символов")
    @Description("Проверка ошибки при пароле меньше 6 символов")
    public void registrationWithShortPasswordTest() {
        user = UserGenerator.generateUserWithShortPassword();
        mainPage.clickLoginButton();
        loginPage.clickRegisterLink();
        registerPage.register(user.getName(), user.getEmail(), user.getPassword());
        assertTrue(registerPage.isPasswordErrorDisplayed(), "Ошибка о слишком коротком пароле не появилась");
    }
}