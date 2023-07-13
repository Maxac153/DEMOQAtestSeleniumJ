package tests;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.AlertsPage;
import resources.TestConstants;

public class TestAlerts {
    @Test
    public void testAlertOne() {
        System.setProperty(TestConstants.WEBDRIVER, TestConstants.CHROMEDRIVER);
        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://demoqa.com/alerts");
            String result = AlertsPage.clickButtonToSeeAlert(driver);
            System.out.println(result);
            // assertEquals("You selected Cancel", result);
        } catch (Exception e) {
            System.out.println("Error message: " + e.getMessage());
        } finally {
            driver.close();
        }
    }

    @Test
    public void testAlertTwo() {
        System.setProperty(TestConstants.WEBDRIVER, TestConstants.CHROMEDRIVER);
        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://demoqa.com/alerts");
            String result = AlertsPage.clickButtonToSeeAlertAfterFiveSeconds(driver);
            System.out.println(result);
            // assertEquals("You selected Cancel", result);
        } catch (Exception e) {
            System.out.println("Error message: " + e.getMessage());
        } finally {
            driver.close();
        }
    }

    @Test
    public void testAlertThree() {
        System.setProperty(TestConstants.WEBDRIVER, TestConstants.CHROMEDRIVER);
        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://demoqa.com/alerts");
            String result = AlertsPage.clickButtonToSeeConfirmBox(driver);
            System.out.println(result);
            assertEquals("You selected Cancel", result);
        } catch (Exception e) {
            System.out.println("Error message: " + e.getMessage());
        } finally {
            driver.close();
        }
    }

    @Test
    public void testAlertFour() {
        System.setProperty(TestConstants.WEBDRIVER, TestConstants.CHROMEDRIVER);
        WebDriver driver = new ChromeDriver();

        try {
            driver.get("https://demoqa.com/alerts");
            String result = AlertsPage.clickButtonToSeePromtBox(driver);
            System.out.println(result);
            assertEquals("You entered Test text", result);
        } catch (Exception e) {
            System.out.println("Error message: " + e.getMessage());
        } finally {
            driver.close();
        }
    }
}
