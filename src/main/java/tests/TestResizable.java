package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.openqa.selenium.Dimension;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.ResizablePage;
import org.testng.Assert;
import wrapper.WrapperClass;

@Epic("Тестирование изменения размера")
@Listeners({io.qameta.allure.testng.AllureTestNg.class})
public class TestResizable extends WrapperClass {
    @Test()
    @Feature("Проверка изменения размера в блоке")
    public void testResizableBox() {
        driver.get("https://demoqa.com/resizable");
        Dimension result = ResizablePage.clickResizableBox(driver, 300, 400);
        try {
            Assert.assertEquals(300, result.height);
            Assert.assertEquals(500, result.width);
        } finally {
            screenshot();
        }
    }

    @Test()
    @Feature("Проверка изменения размера без ограничения блока")
    public void testResizable() {
        Dimension result = ResizablePage.clickResizable(driver, 30, 50);
        try {
            Assert.assertEquals(250, result.height);
            Assert.assertEquals(230, result.width);
        } finally {
            screenshot();
        }
    }
}
