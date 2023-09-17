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

    @Feature("Простое перетаскивания")
    @Test()
    public void TestSimpleDragAndDrop() {
        driver.get("https://demoqa.com/droppable");
        String result = DroppablePage.clickSimpleDragAndDrop(driver);
        String expectedResult = "Dropped!";
        try {
            Assert.assertEquals(result, expectedResult);
        } finally {
            screenshot();
        }
    }

    @Test()
    @Feature("Внешнее перетаскивания")
    public void TestPreventPropogationDragAndDrop1() {
        driver.get("https://demoqa.com/droppable");
        String result = DroppablePage.clickPreventPropogationDragAndDrop(driver, 170, 70);
        String expectedResult = "Dropped! Inner droppable (not greedy)";
        try {
            Assert.assertEquals(result, expectedResult);
        } finally {
            screenshot();
        }
    }

    @Test()
    @Feature("Внутреннее перетаскивание")
    public void TestPreventPropogationDragAndDrop2() {
        driver.get("https://demoqa.com/droppable");
        String result = DroppablePage.clickPreventPropogationDragAndDrop(driver, 215, 70);
        String expectedResult = "Dropped! Dropped!";
        try {
            Assert.assertEquals(result, expectedResult);
        } finally {
            screenshot();
        }
    }
}
