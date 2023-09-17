package tests;

import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SelectMenuPage;
import wrapper.WrapperClass;


public class TestSelectMenu extends WrapperClass {
    @Test()
    @Feature("Select Menu")
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
    @Feature("Select One")
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

    @Test()
    @Feature("Select Old Style Menu")
    public void testSelectOldStyleMenu() {
        driver.get("https://demoqa.com/select-menu");
        String result = SelectMenuPage.selectOldStyleMenu(driver, "Green");
        String expectedResult = "Green";
        try {
            Assert.assertTrue(result.contains(expectedResult));
        } finally {
            screenshot();
        }
    }

    @Test()
    @Feature("Multiselect Drop Down")
    public void testMultiselectDropDown() {
        driver.get("https://demoqa.com/select-menu");
        String result = SelectMenuPage.selectMultiselectDropDown(driver, "Black");
        String expectedResult = "Black";
        try {
            Assert.assertEquals(result, expectedResult);
        } finally {
            screenshot();
        }
    }
}
