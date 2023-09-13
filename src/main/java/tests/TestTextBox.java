package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import pages.TextBoxPage;
import org.testng.annotations.Test;
import wrapper.WrapperClass;


@Epic("Тестирование текстовых полей")
@Listeners({io.qameta.allure.testng.AllureTestNg.class})
public class TestTextBox extends WrapperClass {
    @Test(priority = 1)
    @Feature("Проверка ввода корректных данных")
    public void testTextBox() {
        driver.get("https://demoqa.com/text-box");
        String fullName = "tur123";
        String Email = "verygood@mail.ru";
        String currentAddress = "Товарищи! новая модель организационной" +
                "деятельности позволяет оценить значение новых предложений.";
        String permanentAddress = "Разнообразный и богатый опыт новая модель" +
                "организационной деятельности способствует подготовки и реализации форм развития.";

        String result = TextBoxPage.checkTextBox(driver, fullName, Email, currentAddress, permanentAddress);
        String expectedResult = "Name:" + fullName + "\n" +
                "Email:" + Email + "\n" +
                "Current Address :" + currentAddress + "\n" +
                "Permananet Address :" + permanentAddress;
        try {
            Assert.assertEquals(result, expectedResult);
        } finally {
            screenshot();
        }
    }
}
