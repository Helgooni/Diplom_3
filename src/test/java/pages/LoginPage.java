package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class LoginPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By emailInput = By.xpath(".//label[contains(text(), 'Email')]/following-sibling::input");
    private final By passwordInput = By.xpath(".//label[contains(text(), 'Пароль')]/following-sibling::input");
    private final By loginButton = By.xpath(".//button[contains(text(), 'Войти')]");
    private final By registerLink = By.xpath(".//a[contains(text(), 'Зарегистрироваться')]");
    private final By forgotPasswordLink = By.xpath(".//a[contains(text(), 'Восстановить пароль')]");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    @Step("Ввести email")
    public void enterEmail(String email) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(emailInput)).sendKeys(email);
    }
    @Step("Ввести пароль")
    public void enterPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }
    @Step("Нажать кнопку 'Войти'")
    public void clickLoginButton() {
        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
    }
    @Step("Выполнить вход")
    public void login(String email, String password) {
        enterEmail(email);
        enterPassword(password);
        clickLoginButton();
        wait.until(ExpectedConditions.urlContains("qa-stellarburgers.education-services.ru"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//button[contains(text(), 'Оформить заказ')]")));
    }
    @Step("Нажать ссылку 'Зарегистрироваться'")
    public void clickRegisterLink() {
        wait.until(ExpectedConditions.elementToBeClickable(registerLink)).click();
    }
    @Step("Нажать ссылку 'Восстановить пароль'")
    public void clickForgotPasswordLink() {
        wait.until(ExpectedConditions.elementToBeClickable(forgotPasswordLink)).click();
    }
    @Step("Проверить, что кнопка Войти отображается на странице авторизации")
    public boolean isLoginButtonDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(loginButton)).isDisplayed();
    }
    @Step("Проверить, что открыта страница авторизации")
    public boolean isLoginPageDisplayed() {
        return wait.until(ExpectedConditions.urlContains("/login"));
    }
}