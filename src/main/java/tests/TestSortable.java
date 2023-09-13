package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.SortablePage;
import wrapper.WrapperClass;

@Epic("Тестирование сортировки")
@Listeners({io.qameta.allure.testng.AllureTestNg.class})
public class TestSortable extends WrapperClass  {
    @Test(priority = 1)
    @Feature("Проверка сортировки листа")
    public void testListClick() {
        driver.get("https://demoqa.com/sortable");
        String result = SortablePage.sortableItem(driver);
        String expectedResult = "One\n" + "Three\n" + "Four\n" + "Two\n" + "Five\n" + "Six";
        try {
            Assert.assertEquals(result, expectedResult);
        } finally {
            screenshot();
        }
    }

    @Test(priority = 2)
    @Feature("Проверка сортировки сетки")
    public void testGridClick() {
        String result = SortablePage.sortableGrid(driver);
        String expectedResult = "One\n" + "Three\n" + "Four\n" + "Five\n" + "Six\n" +
                "Seven\n" + "Eight\n" + "Two\n" + "Nine";
        try {
            Assert.assertEquals(result, expectedResult);
        } finally {
            screenshot();
        }
    }
}
