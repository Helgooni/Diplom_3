package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class MainPage {
    private final WebDriver driver;
    private final WebDriverWait wait;

    private final By loginButton = By.xpath(".//button[contains(text(), 'Войти в аккаунт')]");
    private final By profileButton = By.xpath(".//p[contains(text(), 'Личный Кабинет')]");
    private final By constructorButton = By.xpath(".//p[contains(text(), 'Конструктор')]");
    private final By logo = By.xpath(".//div[@class='AppHeader_header__logo__2D0X2']");
    private final By orderButton = By.xpath(".//button[contains(text(), 'Оформить заказ')]");
    private final By bunsTab = By.xpath(".//span[contains(text(), 'Булки')]");
    private final By saucesTab = By.xpath(".//span[contains(text(), 'Соусы')]");
    private final By fillingsTab = By.xpath(".//span[contains(text(), 'Начинки')]");
    private final By activeTab = By.xpath(".//div[contains(@class, 'tab_tab_type_current__2BEPc')]");

    public MainPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    @Step("Нажать кнопку 'Войти в аккаунт'")
    public void clickLoginButton() {

        wait.until(ExpectedConditions.elementToBeClickable(loginButton)).click();
        wait.until(ExpectedConditions.urlContains("/login"));
    }

    @Step("Нажать 'Личный кабинет'")
    public void clickProfileButton() {
        wait.until(ExpectedConditions.elementToBeClickable(profileButton)).click();
        wait.until(ExpectedConditions.urlContains("/account/profile"));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(".//button[contains(text(), 'Выход')]")));
    }

    @Step("Нажать 'Личный кабинет'")
    public void clickProfileButtonNoLogin() {

        wait.until(ExpectedConditions.elementToBeClickable(profileButton)).click();
        wait.until(ExpectedConditions.urlContains("/login"));

    }

    @Step("Нажать 'Конструктор'")
    public void clickConstructor() {
        wait.until(ExpectedConditions.elementToBeClickable(constructorButton)).click();
    }

    @Step("Нажать логотип Stellar Burgers")
    public void clickLogo() {
        wait.until(ExpectedConditions.elementToBeClickable(logo)).click();
    }

    @Step("Проверить, что кнопка 'Оформить заказ' отображается")
    public boolean isOrderButtonDisplayed() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(orderButton)).isDisplayed();
    }

    @Step("Нажать вкладку 'Булки'")
    public void clickBunsTab() {
        wait.until(ExpectedConditions.elementToBeClickable(bunsTab)).click();
        wait.until(ExpectedConditions.textToBe(activeTab, "Булки"));
    }

    @Step("Нажать вкладку 'Соусы'")
    public void clickSaucesTab() {
        wait.until(ExpectedConditions.elementToBeClickable(saucesTab)).click();
        wait.until(ExpectedConditions.textToBe(activeTab, "Соусы"));
    }

    @Step("Нажать вкладку 'Начинки'")
    public void clickFillingsTab() {
        wait.until(ExpectedConditions.elementToBeClickable(fillingsTab)).click();
        wait.until(ExpectedConditions.textToBe(activeTab, "Начинки"));
    }

    @Step("Получить текст активной вкладки")
    public String getActiveTabText() {
        return wait.until(ExpectedConditions.visibilityOfElementLocated(activeTab)).getText();
    }


}