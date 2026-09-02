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

public class LoginTest extends BaseTest {
    private MainPage mainPage;
    private LoginPage loginPage;
    private RegisterPage registerPage;
    private ForgotPasswordPage forgotPasswordPage;
    private UserGenerator.User user;
    @BeforeEach
    @Step("Подготовка к тесту входа - создание пользователя через регистрацию")
    public void init() {
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        forgotPasswordPage = new ForgotPasswordPage(driver);
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
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
        forgotPasswordPage = new ForgotPasswordPage(driver);
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
        mainPage.clickProfileButton();
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