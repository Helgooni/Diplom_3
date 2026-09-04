package tests;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.*;
import utils.UserGenerator;
import static org.junit.jupiter.api.Assertions.assertTrue;
import models.User;
import org.junit.jupiter.api.AfterEach;
import api.ApiClient;

public class LoginTest extends BaseTest {
    private MainPage mainPage;
    private LoginPage loginPage;
    private RegisterPage registerPage;
    private ForgotPasswordPage forgotPasswordPage;
    private User user;
    private String accessToken;
    private ApiClient apiClient;

    @BeforeEach
    @Step("Подготовка к тесту входа - создание пользователя через API")
    public void init() {

        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        forgotPasswordPage = new ForgotPasswordPage(driver);
        apiClient = new ApiClient();
        user = UserGenerator.generateValidUser();
        var response = apiClient.registerUser(user);
        accessToken = apiClient.getAccessToken(response);
        driver.get(BASE_URL);
        waitForPageStable();
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
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
    @DisplayName("Вход через кнопку 'Войти в аккаунт' на главной странице")
    @Description("Проверка входа через главную страницу")
    public void loginViaMainPageTest() {
        mainPage.clickLoginButton();
        loginPage.login(user.getEmail(), user.getPassword());
        assertTrue(mainPage.isOrderButtonDisplayed(), "Кнопка 'Оформить заказ' не отображается");
    }
    @Test
    @DisplayName("Вход через кнопку 'Личный кабинет'")
    @Description("Проверка входа через личный кабинет")
    public void loginViaProfileButtonTest() {
        mainPage.clickProfileButtonNoLogin();
        loginPage.login(user.getEmail(), user.getPassword());
        assertTrue(mainPage.isOrderButtonDisplayed(), "Кнопка 'Оформить заказ' не отображается");
    }
    @Test
    @DisplayName("Вход через кнопку в форме регистрации")
    @Description("Проверка входа через форму регистрации")
    public void loginViaRegisterFormTest() {
        mainPage.clickLoginButton();
        loginPage.clickRegisterLink();
        registerPage.clickLoginLink();
        loginPage.login(user.getEmail(), user.getPassword());
        assertTrue(mainPage.isOrderButtonDisplayed(), "Кнопка 'Оформить заказ' не отображается");
    }
    @Test
    @DisplayName("Вход через кнопку в форме восстановления пароля")
    @Description("Проверка входа через форму восстановления пароля")
    public void loginViaForgotPasswordFormTest() {
        mainPage.clickLoginButton();
        loginPage.clickForgotPasswordLink();
        forgotPasswordPage.clickLoginLink();
        loginPage.login(user.getEmail(), user.getPassword());
        assertTrue(mainPage.isOrderButtonDisplayed(), "Кнопка 'Оформить заказ' не отображается");
    }
}