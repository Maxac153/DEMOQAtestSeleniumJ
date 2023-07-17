package tests;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SortablePage;
import resources.TestConstants;

public class TestSortable {
    @Test
    public void testListClick() {
        System.setProperty(TestConstants.WEBDRIVER, TestConstants.CHROMEDRIVER);
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        try {
            driver.get("https://demoqa.com/sortable");
            String result = SortablePage.sortableItem(driver);
            String expectedResult = "One\n" + "Three\n" + "Four\n" + "Two\n" + "Five\n" + "Six";
            Assert.assertEquals(result, expectedResult);
        } catch (Exception e) {
            System.out.println("Error message: " + e.getMessage());
        } finally {
            driver.close();
        }
    }

    @Test
    public void testGridClick() {
        System.setProperty(TestConstants.WEBDRIVER, TestConstants.CHROMEDRIVER);
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        try {
            driver.get("https://demoqa.com/sortable");
            String result = SortablePage.sortableGrid(driver);
            String expectedResult = "One\n" + "Three\n" + "Four\n" + "Five\n" + "Six\n" +
                    "Seven\n" + "Eight\n" + "Two\n" + "Nine";
            Assert.assertEquals(result, expectedResult);
        } catch (Exception e) {
            System.out.println("Error message:" + e.getMessage());
        } finally {
            driver.close();
        }
    }
}
