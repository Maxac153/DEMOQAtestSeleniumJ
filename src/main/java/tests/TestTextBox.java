package tests;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import pages.TextBoxPage;
import resources.TestConstants;
import org.testng.annotations.Test;

@Listeners({ io.qameta.allure.testng.AllureTestNg.class })
public class TestTextBox {
    @Test
    public void testTextBox() {
        System.setProperty(TestConstants.WEBDRIVER, TestConstants.CHROMEDRIVER);
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        try {
            String fullName = "tur123";
            String Email = "verygood@mail.ru";
            String currentAddress = "Товарищи! новая модель организационной" +
                    "деятельности позволяет оценить значение новых предложений.";
            String permanentAddress = "Разнообразный и богатый опыт новая модель" +
                    "организационной деятельности способствует подготовки и реализации форм развития.";

            driver.get("https://demoqa.com/text-box");
            String result = TextBoxPage.checkTextBox(driver, fullName, Email, currentAddress, permanentAddress);
            String expectedResult = "Name:" + fullName + "\n" +
                                    "Email:" + Email + "\n" +
                                    "Current Address :" + currentAddress + "\n" +
                                    "Permananet Address :" + permanentAddress;

            Assert.assertEquals(expectedResult, result);

        } catch (Exception e) {
            System.out.println("Error message: " + e.getMessage());
        } finally {
            driver.close();
        }

    }
}
