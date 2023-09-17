package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.ButtonsPage;
import wrapper.WrapperClass;

@Listeners({io.qameta.allure.testng.AllureTestNg.class})
@Epic("Тестирование кнопок")
public class TestButtons extends WrapperClass {

    @Test()
    @Feature("Двойное нажатие")
    public void testDoubleClick() {
        driver.get("https://demoqa.com/buttons");
        String result = ButtonsPage.clickDoubleClickMe(driver);
        String expectedResult = "You have done a double click";
        try {
            Assert.assertEquals(result, expectedResult);
        } finally {
            screenshot();
        }
    }

    @Test()
    @Feature("Нажатие правой кнопой")
    public void testRightClick() {
        driver.get("https://demoqa.com/buttons");
        String result = ButtonsPage.clickRightClickMe(driver);
        String expectedResult = "You have done a right click";
        Assert.assertEquals(result, expectedResult);
        try {
            Assert.assertEquals(result, expectedResult);
        } finally {
            screenshot();
        }
    }

    @Test()
    @Feature("Простое нажатие")
    public void testClick() {
        driver.get("https://demoqa.com/buttons");
        String result = ButtonsPage.clickClickMe(driver);
        String expectedResult = "You have done a dynamic click";
        try {
            Assert.assertEquals(result, expectedResult);
        } finally {
            screenshot();
        }
    }
}
