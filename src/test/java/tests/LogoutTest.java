package tests;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.LoginPage;
import pages.MainPage;
import pages.ProfilePage;
import pages.RegisterPage;
import utils.UserGenerator;
import java.time.Duration;
import static org.junit.jupiter.api.Assertions.assertTrue;
import models.User;
import org.junit.jupiter.api.AfterEach;
import api.ApiClient;

public class LogoutTest extends BaseTest {
    private MainPage mainPage;
    private ProfilePage profilePage;
    private LoginPage loginPage;
    private RegisterPage registerPage;
    private User user;
    private String accessToken;
    private ApiClient apiClient;
    private WebDriverWait wait;
    @BeforeEach
    @Step("Подготовка к тесту выхода - создание пользователя через API")
    public void init() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        mainPage = new MainPage(driver);
        profilePage = new ProfilePage(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        apiClient = new ApiClient();
        user = UserGenerator.generateValidUser();
        var response = apiClient.registerUser(user);
        accessToken = apiClient.getAccessToken(response);
        driver.get(BASE_URL);
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
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
    @DisplayName("Выход из аккаунта по кнопке 'Выход'")
    @Description("Проверка выхода из аккаунта")
    public void logoutTest() {
        mainPage.clickLoginButton();
        loginPage.login(user.getEmail(), user.getPassword());
        assertTrue(mainPage.isOrderButtonDisplayed(), "Вход не выполнен");
        mainPage.clickProfileButton();
        profilePage.clickLogoutButton();
        boolean isLoginPageDisplayed = wait.until(ExpectedConditions.urlContains("/login"));
        assertTrue(isLoginPageDisplayed, "Выход не выполнен - не перешли на страницу логина");
        assertTrue(loginPage.isLoginButtonDisplayed(), "Кнопка 'Войти' не отображается на странице логина");
    }
}