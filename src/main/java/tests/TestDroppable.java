package tests;

import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.DroppablePage;
import resources.TestConstants;

import static org.junit.Assert.assertEquals;

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
            assertEquals(expectedResult, result);
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
            assertEquals(expectedResult, result);
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
            assertEquals(expectedResult, result);
        } catch (Exception e) {
            System.out.println("Error message: " + e.getMessage());
        } finally {
            driver.close();
        }
    }
}
