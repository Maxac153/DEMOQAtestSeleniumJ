package tests;


import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.ModalDialogsPage;
import resources.TestConstants;

import static org.junit.Assert.assertEquals;

public class TestModalDialogs {
    @Test
    public void TestSmallModal() {
        System.setProperty(TestConstants.WEBDRIVER, TestConstants.CHROMEDRIVER);
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        try {
            driver.get("https://demoqa.com/modal-dialogs");
            String result = ModalDialogsPage.checkSmallModal(driver);
            String expectedResult = "This is a small modal. It has very less content";
            assertEquals(expectedResult, result);
        } catch (Exception e) {
            System.out.println("Error message: " + e.getMessage());
        } finally {
            driver.close();
        }
    }

    @Test
    public void TestLargeModal() {
        System.setProperty(TestConstants.WEBDRIVER, TestConstants.CHROMEDRIVER);
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        try {
            driver.get("https://demoqa.com/modal-dialogs");
            String result = ModalDialogsPage.checkLargeModal( driver);
            String expectedResult = "Large Modal";
            assertEquals(expectedResult, result);
        } catch (Exception e) {
            System.out.println("Error message: " + e.getMessage());
        } finally {
            driver.close();
        }
    }
}
