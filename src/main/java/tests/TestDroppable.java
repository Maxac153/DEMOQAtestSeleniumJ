package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import pages.DroppablePage;
import org.testng.annotations.Test;
import wrapper.WrapperClass;

@Epic("Тестирование DragAndDrop")
@Listeners({io.qameta.allure.testng.AllureTestNg.class})
public class TestDroppable extends WrapperClass {

    @Feature("Проверка простого перетаскивания")
    @Test(priority = 1)
    public void TestSimpleDragAndDrop() {
        driver.get("https://demoqa.com/droppable");
        String result = DroppablePage.checkSimpleDragAndDrop(driver);
        String expectedResult = "Dropped!";
        try {
            Assert.assertEquals(result, expectedResult);
        } finally {
            screenshot();
        }
    }

    @Test(priority = 2)
    @Feature("Проверка внешнего перетаскивания")
    public void TestPreventPropogationDragAndDrop1() {
        String result = DroppablePage.checkPreventPropogationDragAndDrop(driver, 170, 70);
        String expectedResult = "Dropped! Inner droppable (not greedy)";
        try {
            Assert.assertEquals(result, expectedResult);
        } finally {
            screenshot();
        }
    }

    @Test(priority = 3)
    @Feature("Проверка внутреннего перетаскивания")
    public void TestPreventPropogationDragAndDrop2() {
        String result = DroppablePage.checkPreventPropogationDragAndDrop(driver, 215, 70);
        String expectedResult = "Dropped! Dropped!";
        try {
            Assert.assertEquals(result, expectedResult);
        } finally {
            screenshot();
        }
    }
}
