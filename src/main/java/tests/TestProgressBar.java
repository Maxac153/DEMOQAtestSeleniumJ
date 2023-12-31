package tests;

import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.ProgressBarPage;
import wrapper.WrapperClass;

@Epic("Тестирование индекатора")
@Listeners({io.qameta.allure.testng.AllureTestNg.class})
public class TestProgressBar extends WrapperClass {
    @Test()
    @Feature("Загрузка индикатора")
    public void TestProgressBar() {
        driver.get("https://demoqa.com/progress-bar");
        try {
            String result = ProgressBarPage.clickProgressBar(driver);
            String expectedResult = "100";
            Assert.assertEquals(result, expectedResult);
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            screenshot();
        }
    }
}
