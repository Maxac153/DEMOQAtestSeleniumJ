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
    @Feature("Проверка двойного нажатия")
    public void testDoubleClick() {
        driver.get("https://demoqa.com/buttons");
        String result = ButtonsPage.checkDoubleClickMe(driver);
        String expectedResult = "You have done a double click";
        try {
            Assert.assertEquals(result, expectedResult);
        } finally {
            driver.close();
        }
    }

    @Test()
    @Feature("Проверка нажатия правой кнопки")
    public void testRightClick() {
        String result = ButtonsPage.checkRightClickMe(driver);
        String expectedResult = "You have done a right click";
        Assert.assertEquals(result, expectedResult);
        try {
            Assert.assertEquals(result, expectedResult);
        } finally {
            screenshot();
        }
    }

    @Test()
    @Feature("Проверка простого нажатия")
    public void testClick() {
        String result = ButtonsPage.checkClickMe(driver);
        String expectedResult = "You have done a dynamic click";
        try {
            Assert.assertEquals(result, expectedResult);
        } finally {
            screenshot();
        }
    }
}
