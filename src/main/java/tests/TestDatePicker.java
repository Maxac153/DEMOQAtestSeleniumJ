package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.DatePickerPage;
import wrapper.WrapperClass;

@Listeners({io.qameta.allure.testng.AllureTestNg.class})
@Epic("Тестирование изменеия даты")
public class TestDatePicker extends WrapperClass {

    @Test()
    @Feature("Проверка даты")
    public void testDate() {
        driver.get("https://demoqa.com/date-picker");
        String expectedResult = "07/16/2021";
        String result = DatePickerPage.clickDate(driver, expectedResult);
        try {
            Assert.assertEquals(result, expectedResult);
        } finally {
            screenshot();
        }
    }

    @Test()
    @Feature("Проверка даты и времени")
    public void testDateTime() {
        String expectedResult = "July 16, 2023 3:14 PM";
        String result = DatePickerPage.clickDateAndTime(driver, expectedResult);
        try {
            Assert.assertEquals(result, expectedResult);
        } finally {
            screenshot();
        }
    }
}
