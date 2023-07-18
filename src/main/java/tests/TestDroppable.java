package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import pages.DroppablePage;
import resources.TestConstants;
import org.testng.annotations.Test;

@Listeners({ io.qameta.allure.testng.AllureTestNg.class })
public class TestDroppable {
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
