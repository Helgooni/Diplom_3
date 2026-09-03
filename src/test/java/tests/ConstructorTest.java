package tests;

import base.BaseTest;
import io.qameta.allure.Description;
import io.qameta.allure.Step;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pages.MainPage;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.AfterEach;

public class ConstructorTest extends BaseTest {
    private MainPage mainPage;
    @BeforeEach
    @Step("Подготовка к тесту конструктора")
    public void init() {
        mainPage = new MainPage(driver);
    }

    @AfterEach
    @Step("Очистка после теста: закрытие браузера")
    public void cleanUp() {
        if (driver != null) {
            driver.quit();
        }
    }
    @Test
    @DisplayName("Переход к разделу 'Булки'")
    @Description("Проверка, что раздел 'Булки' отображается")
    public void switchToBunsTest() {
        mainPage.clickSaucesTab();
        mainPage.clickBunsTab();

        assertEquals("Булки", mainPage.getActiveTabText(), "Раздел 'Булки' не отображается");
    }
    @Test
    @DisplayName("Переход к разделу 'Соусы'")
    @Description("Проверка, что раздел 'Соусы' отображается")
    public void switchToSaucesTest() {
        mainPage.clickSaucesTab();
        assertEquals("Соусы", mainPage.getActiveTabText(), "Раздел 'Соусы' не отображается");
    }
    @Test
    @DisplayName("Переход к разделу 'Начинки'")
    @Description("Проверка, что раздел 'Начинки' отображается")
    public void switchToFillingsTest() {
        mainPage.clickFillingsTab();
        assertEquals("Начинки", mainPage.getActiveTabText(), "Раздел 'Начинки' не отображается");
    }
}