package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.ModalDialogsPage;
import wrapper.WrapperClass;


@Epic("Тестирование модальных окон")
@Listeners({io.qameta.allure.testng.AllureTestNg.class})
public class TestModalDialogs extends WrapperClass {
    @Test()
    @Feature("Активность кнопки (маленького размера)")
    public void TestSmallModal() {
        driver.get("https://demoqa.com/modal-dialogs");
        String result = ModalDialogsPage.clickSmallModal(driver);
        String expectedResult = "Small Modal";
        try {
            Assert.assertEquals(result, expectedResult);
        } finally {
            screenshot();
        }
    }

    @Test()
    @Feature("Активность кнопки (большого размера)")
    public void TestLargeModal() {
        driver.get("https://demoqa.com/modal-dialogs");
        String result = ModalDialogsPage.clickLargeModal(driver);
        String expectedResult = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. " +
                "Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown " +
                "printer took a galley of type and scrambled it to make a type specimen book. It has survived " +
                "not only five centuries, but also the leap into electronic typesetting, " +
                "remaining essentially unchanged. It was popularised in the 1960s with the release of " +
                "Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing " +
                "software like Aldus PageMaker including versions of Lorem Ipsum.";
        try {
            Assert.assertEquals(result, expectedResult);
        } finally {
            screenshot();
        }
    }
}
