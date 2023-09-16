package tests;

import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import pages.SelectMenuPage;
import wrapper.WrapperClass;


public class TestSelectMenu extends WrapperClass {
    @BeforeTest
    public void setUpDriver() {
        driver.get("https://demoqa.com/select-menu");
    }

    @Test()
    @Feature("")
    public void testSelectValue() {
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
        String result = SelectMenuPage.selectOne(driver, "Ms.");
        String expectedResult = "Ms.";
        try {
            Assert.assertEquals(result, expectedResult);
        } finally {
            screenshot();
        }
    }

    @Test()
    @Feature("")
    public void testSelectOldStyleMenu() {
        String result = SelectMenuPage.selectOldStyleMenu(driver, "Green");
        String expectedResult = "Green";
        try {
            System.out.println(result);
            Assert.assertTrue(result.contains(expectedResult));
        } finally {
            screenshot();
        }
    }

    @Test()
    @Feature("")
    public void testMultiselectDropDown() {
        String result = SelectMenuPage.selectMultiselectDropDown(driver, "Black");
        String expectedResult = "Black";
        try {
            Assert.assertEquals(result, expectedResult);
        } finally {
            screenshot();
        }
    }
}
