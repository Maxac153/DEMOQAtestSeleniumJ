package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.DynamicPropertiesPage;
import wrapper.WrapperClass;

@Listeners({io.qameta.allure.testng.AllureTestNg.class})
@Epic("Тестирование динамических свойст")
public class TestDynamicProperties extends WrapperClass  {
    @Test(priority = 1)
    @Feature("Проверка активности кнопки")
    public void TestButtonEnableAfter() {
        driver.get("https://demoqa.com/dynamic-properties");
        String result = DynamicPropertiesPage.checkButtonEnableAfter(driver);
        String expectedResult = null;
        try {
            Assert.assertEquals(result, expectedResult);
        } finally {
            screenshot();
        }
    }

    @Test(priority = 2)
    @Feature("Проверка цвета кнопки")
    public void TestColorChange() {
        String result = DynamicPropertiesPage.checkColorChange(driver);
        String expectedResult = "rgba(220, 53, 69, 1)";
        try {
            Assert.assertEquals(result, expectedResult);
        } finally {
            screenshot();
        }
    }

    @Test(priority = 3)
    @Feature("Проверка появления кнопки")
    public void TestVisibleAfter() {
        String result = DynamicPropertiesPage.checkVisibleAfter(driver);
        String expectedResult = "Visible After 5 Seconds";
        try {
            Assert.assertEquals(result, expectedResult);
        } finally {
            screenshot();
        }
    }
}
