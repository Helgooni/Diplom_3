package base;

import factory.WebDriverFactory;
import io.qameta.allure.Step;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BaseTest {
    protected static final String BASE_URL = "https://qa-stellarburgers.education-services.ru/";
    protected WebDriver driver;
    @BeforeEach
    @Step("Открытие страницы браузера")
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        driver = WebDriverFactory.getDriver(browser);
        driver.manage().window().maximize();
        driver.get(BASE_URL);

    }
    protected void waitForPageStable() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

}
