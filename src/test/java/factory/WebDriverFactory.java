package factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;

public class WebDriverFactory {

    public static WebDriver getDriver(String browserName) {
        switch (browserName.toLowerCase()) {
            case "chrome":
                WebDriverManager.chromedriver().setup();
                return new ChromeDriver();
            case "firefox":
                WebDriverManager.firefoxdriver().setup();
                return new FirefoxDriver();
            case "yandex":
                System.setProperty("webdriver.chrome.driver", "src/test/resources/yandexdriver.exe");

                ChromeOptions options = new ChromeOptions();
                options.setBinary("C:\\Program Files (x86)\\Yandex\\YandexBrowser\\Application\\browser.exe");

                return new ChromeDriver(options);
            default:
                throw new IllegalArgumentException("Browser not supported: " + browserName);
        }
    }
}