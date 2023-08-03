package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.AlertsPage;
import resources.TestConstants;
import wrapper.WrapperClass;

@Epic("Тестирование оповещений")
@Listeners({io.qameta.allure.testng.AllureTestNg.class})
public class TestAlerts extends WrapperClass {

    @Test(priority = 1)
    @Feature("Проверка простого оповещения")
    public void testAlertOne() {
        driver.get("https://demoqa.com/alerts");
        String result = AlertsPage.clickButtonToSeeAlert(driver);
        String expectedResult = "You clicked a button";
        try {
            Assert.assertEquals(result, expectedResult);
        } finally {
            screenshot();
        }
    }

    @Test(priority = 2)
    @Feature("Проверка оповещения который появиться после 5 секунд")
    public void testAlertTwo() {
        String result = AlertsPage.clickButtonToSeeAlertAfterFiveSeconds(driver);
        String expectedResult = "This alert appeared after 5 seconds";
        try {
            Assert.assertEquals(result, expectedResult);
        } finally {
            screenshot();
        }
    }

    @Test(priority = 3)
    @Feature("Проверка окна подтверждения")
    public void testAlertThree() {
        String result = AlertsPage.clickButtonToSeeConfirmBox(driver);
        String expectedResult = "You selected Cancel";
        try {
            Assert.assertEquals(result, expectedResult);
        } finally {
            screenshot();
        }
    }

    @Test(priority = 4)
    @Feature("Проверка окна подсказки")
    public void testAlertFour() {
        String result = AlertsPage.clickButtonToSeePromtBox(WrapperClass.driver);
        String expectedResult = "You entered Test text";
        try {
            Assert.assertEquals(result, expectedResult);
        } finally {
            WrapperClass.screenshot();
        }
    }
}
