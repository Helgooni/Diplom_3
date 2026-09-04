package tests;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.LoginPage;
import pages.MainPage;
import pages.ProfilePage;
import pages.RegisterPage;
import utils.UserGenerator;
import static org.junit.jupiter.api.Assertions.assertTrue;
import api.ApiClient;
import models.User;
import org.junit.jupiter.api.AfterEach;

public class ProfileTest extends BaseTest {
    private MainPage mainPage;
    private ProfilePage profilePage;
    private LoginPage loginPage;
    private RegisterPage registerPage;
    private User user;
    private String accessToken;
    private ApiClient apiClient;
    @BeforeEach
    @Step("Подготовка к тесту профиля - создание пользователя через API")
    public void init() {
        mainPage = new MainPage(driver);
        profilePage = new ProfilePage(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
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
    @DisplayName("Переход в личный кабинет")
    @Description("Проверка перехода в личный кабинет")
    public void navigateToProfileTest() {
        mainPage.clickLoginButton();
        loginPage.login(user.getEmail(), user.getPassword());
        mainPage.clickProfileButton();
        assertTrue(profilePage.isLogoutButtonDisplayed(), "Кнопка 'Выйти' не отображается");
    }
    @Test
    @DisplayName("Переход из личного кабинета в конструктор по кнопке 'Конструктор'")
    @Description("Проверка перехода из личного кабинета в конструктор")
    public void navigateFromProfileToConstructorTest() {
        mainPage.clickLoginButton();
        loginPage.login(user.getEmail(), user.getPassword());
        mainPage.clickProfileButton();
        profilePage.clickConstructor();
        assertTrue(mainPage.isOrderButtonDisplayed(), "Кнопка 'Оформить заказ' не отображается");
    }
    @Test
    @DisplayName("Переход из личного кабинета на главную по логотипу")
    @Description("Проверка перехода из личного кабинета на главную через логотип")
    public void navigateFromProfileToMainViaLogoTest() {
        mainPage.clickLoginButton();
        loginPage.login(user.getEmail(), user.getPassword());
        mainPage.clickProfileButton();
        profilePage.clickLogo();
        assertTrue(mainPage.isOrderButtonDisplayed(), "Кнопка 'Оформить заказ' не отображается");
    }
}