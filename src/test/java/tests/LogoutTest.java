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

public class LogoutTest extends BaseTest {
    private MainPage mainPage;
    private ProfilePage profilePage;
    private LoginPage loginPage;
    private RegisterPage registerPage;
    private UserGenerator.User user;
    private WebDriverWait wait;
    @BeforeEach
    @Step("Подготовка к тесту выхода - создание пользователя через регистрацию")
    public void init() {
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        mainPage = new MainPage(driver);
        profilePage = new ProfilePage(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        user = UserGenerator.generateValidUser();
        mainPage.clickLoginButton();
        loginPage.clickRegisterLink();
        registerPage.register(user.getName(), user.getEmail(), user.getPassword());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        driver.get("https://qa-stellarburgers.education-services.ru/");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        mainPage = new MainPage(driver);
        profilePage = new ProfilePage(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
    }
    @Test
    @DisplayName("Выход из аккаунта по кнопке 'Выход'")
    @Description("Проверка выхода из аккаунта")
    public void logoutTest() {

        mainPage.clickLoginButton();
        loginPage.login(user.getEmail(), user.getPassword());
        assertTrue(mainPage.isOrderButtonDisplayed(), "Вход не выполнен");
        mainPage.clickProfileButton();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        profilePage.clickLogoutButton();
        boolean isLoginPageDisplayed = wait.until(ExpectedConditions.urlContains("/login"));
        assertTrue(isLoginPageDisplayed, "Выход не выполнен - не перешли на страницу логина");
        assertTrue(loginPage.isLoginButtonDisplayed(), "Кнопка 'Войти' не отображается на странице логина");
    }
}