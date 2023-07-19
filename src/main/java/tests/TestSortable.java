package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.SortablePage;
import resources.TestConstants;

@Epic("Тестирование сортировки")
@Listeners({io.qameta.allure.testng.AllureTestNg.class})
public class TestSortable {
    @Test
    @Feature("Проверка сортировки листа")
    public void testListClick() {
        System.setProperty(TestConstants.WEBDRIVER, TestConstants.CHROMEDRIVER);
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        try {
            driver.get("https://demoqa.com/sortable");
            String result = SortablePage.sortableItem(driver);
            String expectedResult = "One\n" + "Three\n" + "Four\n" + "Two\n" + "Five\n" + "Six";
            Assert.assertEquals(expectedResult, result);
        } catch (Exception e) {
            System.out.println("Error message: " + e.getMessage());
        } finally {
            driver.close();
        }
    }

    @Test
    @Feature("Проверка сортировки сетки")
    public void testGridClick() {
        System.setProperty(TestConstants.WEBDRIVER, TestConstants.CHROMEDRIVER);
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        try {
            driver.get("https://demoqa.com/sortable");
            String result = SortablePage.sortableGrid(driver);
            String expectedResult = "One\n" + "Three\n" + "Four\n" + "Five\n" + "Six\n" +
                    "Seven\n" + "Eight\n" + "Two\n" + "Nine";
            Assert.assertEquals(expectedResult, result);
        } catch (Exception e) {
            System.out.println("Error message:" + e.getMessage());
        } finally {
            driver.close();
        }
    }
}
