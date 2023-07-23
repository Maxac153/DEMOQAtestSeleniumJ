package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.BrowserWindowsPage;
import wrapper.WrapperClass;

@Epic("Тестирование окон")
@Listeners({io.qameta.allure.testng.AllureTestNg.class})
public class TestBrowserWindows extends WrapperClass {
    public TestBrowserWindows() {
        super("https://demoqa.com/browser-windows");
    }

    @Test(priority = 1)
    @Feature("Проверка новой вкладки")
    public void TestNewTab() {
        String result = BrowserWindowsPage.clickButtonNewTab(driver);
        String expectedResult = "This is a sample page";
        try {
            Assert.assertEquals(result, expectedResult);
        } finally {
            screenshot();
        }
    }

    @Test(priority = 2)
    @Feature("Проверка нового окна")
    public void TestNewWindow() {
        String result = BrowserWindowsPage.clickButtonNewWindow(driver);
        String expectedResult = "This is a sample page";
        Assert.assertEquals(result, expectedResult);
        try {
            Assert.assertEquals(result, expectedResult);
        } finally {
            screenshot();
        }
    }

    @Test(priority = 3)
    @Feature("Проверка нового окна с сообщением")
    public void TestNewWindowMessage() {
        String result = BrowserWindowsPage.clickButtonNewWindowMessage(driver);
        String expectedResult = "Knowledge increases by sharing but not by saving. Please share " +
                "this website with your friends and in your organization.";
        try {
            Assert.assertEquals(result, expectedResult);
        } finally {
            screenshot();
        }
    }
}
