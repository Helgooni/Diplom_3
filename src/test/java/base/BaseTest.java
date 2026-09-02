package base;

import factory.WebDriverFactory;
import io.qameta.allure.Step;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;

public class BaseTest {

    protected WebDriver driver;

    @BeforeEach
    //@Step("Открытие страницы браузера")
    public void setUp() {
        String browser = System.getProperty("browser", "chrome");
        driver = WebDriverFactory.getDriver(browser);
        driver.manage().window().maximize();
        driver.get("https://qa-stellarburgers.education-services.ru/");
    }

    @AfterEach
    //@Step("Закрытие страницы браузера")
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}