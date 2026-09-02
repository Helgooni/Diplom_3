package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class ProfilePage {
    private final WebDriver driver;
    private final WebDriverWait wait;
    private final By logoutButton = By.xpath(".//button[contains(text(), 'Выход')]");
    private final By constructorButton = By.xpath(".//p[contains(text(), 'Конструктор')]");
    private final By logo = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']");
    public ProfilePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    @Step("Нажать кнопку 'Выход'")
    public void clickLogoutButton() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        wait.until(ExpectedConditions.elementToBeClickable(logoutButton)).click();
    }
    @Step("Нажать 'Конструктор'")
    public void clickConstructor() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        wait.until(ExpectedConditions.elementToBeClickable(constructorButton)).click();
    }
    @Step("Нажать на логотип")
    public void clickLogo() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        wait.until(ExpectedConditions.elementToBeClickable(logo)).click();
    }
    @Step("Проверить, что кнопка 'Выход' отображается")
    public boolean isLogoutButtonDisplayed() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return wait.until(ExpectedConditions.visibilityOfElementLocated(logoutButton)).isDisplayed();
    }
}