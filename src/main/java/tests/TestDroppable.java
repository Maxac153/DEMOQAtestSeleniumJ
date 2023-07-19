package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import pages.DroppablePage;
import resources.TestConstants;
import org.testng.annotations.Test;

@Epic("Тестирование DragAndDrop")
@Listeners({ io.qameta.allure.testng.AllureTestNg.class })
public class TestDroppable {
    @Feature("Проверка простого перетаскивания")
    @Test
    public void TestSimpleDragAndDrop() {
        System.setProperty(TestConstants.WEBDRIVER, TestConstants.CHROMEDRIVER);
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        try {
            driver.get("https://demoqa.com/droppable");
            String result = DroppablePage.checkSimpleDragAndDrop(driver);
            String expectedResult = "Dropped!";
            Assert.assertEquals(expectedResult, result);
        } catch (Exception e) {
            System.out.println("Error message: " + e.getMessage());
        } finally {
            driver.close();
        }
    }

    @Test
    @Feature("Проверка внешнего перетаскивания")
    public void TestPreventPropogationDragAndDrop1() {
        System.setProperty(TestConstants.WEBDRIVER, TestConstants.CHROMEDRIVER);
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        try {
            driver.get("https://demoqa.com/droppable");
            String result = DroppablePage.checkPreventPropogationDragAndDrop(driver, 170, 70);
            String expectedResult = "Dropped! Inner droppable (not greedy)";
            Assert.assertEquals(expectedResult, result);
        } catch (Exception e) {
            System.out.println("Error message: " + e.getMessage());
        } finally {
            driver.close();
        }
    }

    @Test
    @Feature("Проверка внутреннего перетаскивания")
    public void TestPreventPropogationDragAndDrop2() {
        System.setProperty(TestConstants.WEBDRIVER, TestConstants.CHROMEDRIVER);
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        try {
            driver.get("https://demoqa.com/droppable");
            String result = DroppablePage.checkPreventPropogationDragAndDrop(driver, 215, 70);
            String expectedResult = "Dropped! Dropped!";
            Assert.assertEquals(expectedResult, result);
        } catch (Exception e) {
            System.out.println("Error message: " + e.getMessage());
        } finally {
            driver.close();
        }
    }
}
