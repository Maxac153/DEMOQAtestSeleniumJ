package tests;

import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SelectMenuPage;
import wrapper.WrapperClass;

import static wrapper.WrapperClass.screenshot;

public class TestSelectMenu extends WrapperClass {
    @Test()
    @Feature("")
    public void testSelectValue() {
        driver.get("https://demoqa.com/select-menu");
        String result = SelectMenuPage.selectValue(driver, "Group 2, option 1");
        String expectedResult = "Group 2, option 1";
        try {
            Assert.assertEquals(result, expectedResult);
        } finally {
            screenshot();
        }
    }

    @Test()
    @Feature("")
    public void testSelectOne() {
        driver.get("https://demoqa.com/select-menu");
        String result = SelectMenuPage.selectOne(driver, "Ms.");
        String expectedResult = "Ms.";
        try {
            Assert.assertEquals(result, expectedResult);
        } finally {
            screenshot();
        }
    }
}
