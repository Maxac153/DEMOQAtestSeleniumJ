package ui.demoqa.selenium.pages.elements;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.demoqa.selenium.common.BasePage;
import ui.demoqa.selenium.locators.elements.WebTableLocators;
import ui.demoqa.selenium.models.elements.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WebTablePage extends BasePage {

    public WebTablePage(WebDriver driver, String url) {
        super(driver, url);
    }

    @Step("Создание нового пользователя")
    public List<String> addNewPerson(Person person) {
        elementIsVisible(WebTableLocators.ADD_BUTTON).click();
        elementIsVisible(WebTableLocators.FIRSTNAME_INPUT).sendKeys(person.firstname());
        elementIsVisible(WebTableLocators.LASTNAME_INPUT).sendKeys(person.lastname());
        elementIsVisible(WebTableLocators.EMAIL_INPUT).sendKeys(person.email());
        elementIsVisible(WebTableLocators.AGE_INPUT).sendKeys(String.valueOf(person.age()));
        elementIsVisible(WebTableLocators.SALARY_INPUT).sendKeys(String.valueOf(person.salary()));
        elementIsVisible(WebTableLocators.DEPARTMENT_INPUT).sendKeys(person.department());
        elementIsVisible(WebTableLocators.SUBMIT).click();

        // Ожидание исчезновения формы
        wait(10).until(ExpectedConditions.invisibilityOfElementLocated(WebTableLocators.REGISTRATION_FORM));

        return List.of(person.firstname(), person.lastname(), String.valueOf(person.age()),
                person.email(), String.valueOf(person.salary()), person.department());
    }

    @Step("Получение всех пользователей")
    public List<String> getPersons() {
        List<WebElement> peopleList = elementsAreVisible(WebTableLocators.FULL_PEOPLE_LIST);
        return peopleList.stream()
                .map(item -> item.getText().replace("\n", " "))
                .collect(Collectors.toList());
    }

    @Step("Поиск пользователя")
    public void searchSomePerson(String keyWord) {
        elementIsVisible(WebTableLocators.SEARCH_INPUT).sendKeys(keyWord);
    }

    @Step("Поиск пользователя")
    public String getSearchPerson() {
        // Явно ждем, что строка таблицы с результатами поиска стала видимой
        WebElement row = elementIsVisible(By.xpath("//div[contains(@class, 'rt-tr') and .//span[@title='Delete']]"));
        return row.getText();
    }

    @Step("Обновить личные данные")
    public String updatePersonInfo(String age) {
        elementIsVisible(WebTableLocators.UPDATE_BUTTON).click();
        WebElement ageInput = elementIsVisible(WebTableLocators.AGE_INPUT);
        ageInput.clear();
        ageInput.sendKeys(age);
        elementIsVisible(WebTableLocators.SUBMIT).click();
        return age;
    }

    @Step("Удалить пользователя")
    public void deletePerson() {
        elementIsVisible(WebTableLocators.DELETE_BUTTON).click();
    }

    @Step("Поиск удалённого пользователя")
    public String searchDeletedPerson() {
        return driver.findElement(WebTableLocators.NO_ROWS_FOUND).getText();
    }

    @Step("Выбрать до определенного количества строк")
    public List<Integer> selectUpToSomeRows() {
        int[] counts = {10, 20, 30, 40, 50};
        List<Integer> data = new ArrayList<>();

        for (int x : counts) {
            WebElement countRowButton = elementIsVisible(WebTableLocators.COUNT_ROW_LIST);
            goToElement(countRowButton);
            countRowButton.click();
            elementIsVisible(By.cssSelector(String.format("option[value='%d']", x))).click();
            data.add(checkCountRows());
        }
        return data;
    }

    @Step("Проверить количество строк")
    public int checkCountRows() {
        List<WebElement> listRows = wait(10).until(ExpectedConditions.presenceOfAllElementsLocatedBy(WebTableLocators.FULL_PEOPLE_LIST));
        return listRows.size();
    }
}
