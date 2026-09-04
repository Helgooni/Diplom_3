package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class RegisterPage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By nameInput = By.xpath(".//label[contains(text(), 'Имя')]/following-sibling::input");
    private final By emailInput = By.xpath(".//label[contains(text(), 'Email')]/following-sibling::input");
    private final By passwordInput = By.xpath(".//label[contains(text(), 'Пароль')]/following-sibling::input");
    private final By registerButton = By.xpath(".//button[contains(text(), 'Зарегистрироваться')]");
    private final By loginLink = By.xpath(".//a[contains(text(), 'Войти')]");
    private final By passwordError = By.xpath(".//p[contains(text(), 'Некорректный пароль')]");
    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    @Step("Заполнить поле 'Имя'")
    public void enterName(String name) {
        wait.until(ExpectedConditions.visibilityOfElementLocated(nameInput)).sendKeys(name);
    }
    @Step("Заполнить поле 'Email'")
    public void enterEmail(String email) {
        driver.findElement(emailInput).sendKeys(email);
    }
    @Step("Заполнить поле 'Пароль'")
    public void enterPassword(String password) {
        driver.findElement(passwordInput).sendKeys(password);
    }
    @Step("Нажать кнопку 'Зарегистрироваться'")
    public void clickRegisterButton() {
        driver.findElement(registerButton).click();
    }
    @Step("Регистрация пользователя")
    public void register(String name, String email, String password) {
        enterName(name);
        enterEmail(email);
        enterPassword(password);
        clickRegisterButton();
    }
    @Step("Нажать ссылку 'Войти' на странице регистрации")
    public void clickLoginLink() {
        wait.until(ExpectedConditions.elementToBeClickable(loginLink)).click();
    }
    @Step("Проверить, что отображается ошибка о некорректном пароле")
    public boolean isPasswordErrorDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(passwordError)).isDisplayed();
    }
    @Step("Проверить, что регистрация успешна - переход на страницу входа")
    public boolean isRegistrationSuccess() {
        return wait.until(ExpectedConditions.urlContains("/login"));
    }
}