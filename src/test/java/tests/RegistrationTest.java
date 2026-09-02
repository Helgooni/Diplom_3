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

public class RegistrationTest extends BaseTest {
    private MainPage mainPage;
    private LoginPage loginPage;
    private RegisterPage registerPage;
    @BeforeEach
    @Step("Подготовка к тесту регистрации")
    public void init() {
        mainPage = new MainPage(driver);
        loginPage = new LoginPage(driver);
        registerPage = new RegisterPage(driver);
    }
    @Test
    @DisplayName("Успешная регистрация")
    @Description("Проверка регистрации с корректными данными")
    public void successfulRegistrationTest() {
        var user = UserGenerator.generateValidUser();
        mainPage.clickLoginButton();
        loginPage.clickRegisterLink();
        registerPage.register(user.getName(), user.getEmail(), user.getPassword());
        assertTrue(registerPage.isRegistrationSuccess(), "Регистрация завершилась не успешно");
    }
    @Test
    @DisplayName("Ошибка при при пароле меньше 6 символов")
    @Description("Проверка ошибки при пароле меньше 6 символов")
    public void registrationWithShortPasswordTest() {
        var user = UserGenerator.generateUserWithShortPassword();
        mainPage.clickLoginButton();
        loginPage.clickRegisterLink();
        registerPage.register(user.getName(), user.getEmail(), user.getPassword());
        assertTrue(registerPage.isPasswordErrorDisplayed(), "Ошибка о слишком коротком пароле не появилась");
    }
}