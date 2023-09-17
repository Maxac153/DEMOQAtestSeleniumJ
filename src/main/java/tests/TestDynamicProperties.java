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
    @Test()
    @Feature("Активной кнопки")
    public void TestButtonEnableAfter() {
        driver.get("https://demoqa.com/dynamic-properties");
        String result = DynamicPropertiesPage.clickButtonEnableAfter(driver);
        String expectedResult = null;
        try {
            Assert.assertEquals(result, expectedResult);
        } finally {
            screenshot();
        }
    }

    @Test()
    @Feature("Цвет кнопки")
    public void TestColorChange() {
        driver.get("https://demoqa.com/dynamic-properties");
        String result = DynamicPropertiesPage.clickColorChange(driver);
        String expectedResult = "rgba(220, 53, 69, 1)";
        try {
            Assert.assertEquals(result, expectedResult);
        } finally {
            screenshot();
        }
    }

    @Test()
    @Feature("Появления кнопки")
    public void TestVisibleAfter() {
        driver.get("https://demoqa.com/dynamic-properties");
        String result = DynamicPropertiesPage.clickVisibleAfter(driver);
        String expectedResult = "Visible After 5 Seconds";
        try {
            Assert.assertEquals(result, expectedResult);
        } finally {
            screenshot();
        }
    }
}
