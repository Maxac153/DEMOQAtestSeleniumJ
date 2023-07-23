package tests;


import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import pages.TextBoxPage;
import resources.TestConstants;
import org.testng.annotations.Test;
import wrapper.WrapperClass;

import java.io.ByteArrayInputStream;

@Epic("Тестирование текстовых полей")
@Listeners({io.qameta.allure.testng.AllureTestNg.class})
public class TestTextBox extends WrapperClass {
    public TestTextBox() {
        super("https://demoqa.com/text-box");
    }

    @Test(priority = 1)
    @Feature("Проверка ввода корректных данных")
    public void testTextBox() {
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
