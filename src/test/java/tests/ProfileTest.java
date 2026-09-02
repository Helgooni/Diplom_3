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

public class ProfileTest extends BaseTest {
    private MainPage mainPage;
    private ProfilePage profilePage;
    private LoginPage loginPage;
    private RegisterPage registerPage;
    private UserGenerator.User user;
    @BeforeEach
    @Step("Подготовка к тесту профиля - создание пользователя через регистрацию")
    public void init() {
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