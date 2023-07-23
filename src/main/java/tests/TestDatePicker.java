package tests;

import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.DatePickerPage;
import resources.TestConstants;
import wrapper.WrapperClass;

import java.io.ByteArrayInputStream;

@Listeners({io.qameta.allure.testng.AllureTestNg.class})
@Epic("Тестирование изменеия даты")
public class TestDatePicker extends WrapperClass {
    public TestDatePicker() {
        super("https://demoqa.com/date-picker");
    }

    @Test(priority = 1)
    @Feature("Проверка даты")
    public void testDate() {
        String expectedResult = "07/16/2021";
        String result = DatePickerPage.checkDate(driver, expectedResult);
        try {
            Assert.assertEquals(result, expectedResult);
        } finally {
            screenshot();
        }
    }

    @Test(priority = 2)
    @Feature("Проверка даты и времени")
    public void testDateTime() {
        String expectedResult = "July 16, 2023 3:14 PM";
        String result = DatePickerPage.checkDateAndTime(driver, expectedResult);
        try {
            Assert.assertEquals(result, expectedResult);
        } finally {
            screenshot();
        }
    }
}
