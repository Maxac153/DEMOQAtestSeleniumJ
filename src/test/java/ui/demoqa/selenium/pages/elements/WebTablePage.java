package ui.demoqa.selenium.pages.elements;

import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ui.demoqa.selenium.common.BasePage;
import ui.demoqa.selenium.locators.elements.WebTableLocators;
import ui.demoqa.selenium.models.elements.Person;

import java.util.ArrayList;
import java.util.List;

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
        elementIsInvisible(WebTableLocators.REGISTRATION_FORM, 10);

        return List.of(person.firstname(), person.lastname(), String.valueOf(person.age()),
                person.email(), String.valueOf(person.salary()), person.department());
    }

    @Step("Получить список пользователей")
    public List<String> getPersons() {
        elementIsVisible(WebTableLocators.ROWS);

        List<String> result = new ArrayList<>();
        for (WebElement row : driver.findElements(WebTableLocators.ROWS)) {
            result.add(rowToString(row));
        }
        return result;
    }

    @Step("Поиск пользователя")
    public void searchSomePerson(String keyWord) {
        elementIsVisible(WebTableLocators.SEARCH_INPUT).sendKeys(keyWord);
    }

    @Step("Получить строку пользователя")
    public String getSearchPerson(String email) {
        WebElement row = elementIsVisible(WebTableLocators.rowWithEmail(email));
        return rowToString(row);
    }

    @Step("Обновить личные данные")
    public String updatePersonInfo(String age) {
        elementIsVisible(WebTableLocators.UPDATE_BUTTON).click();
        WebElement ageInput = elementIsVisible(WebTableLocators.AGE_INPUT);
        ageInput.clear();
        ageInput.sendKeys(age);
        elementIsVisible(WebTableLocators.SUBMIT).click();

        // дождаться закрытия модалки
        elementIsInvisible(WebTableLocators.REGISTRATION_FORM, 10);
        return age;
    }

    @Step("Удалить пользователя")
    public void deletePerson() {
        elementIsVisible(WebTableLocators.DELETE_BUTTON).click();
    }

    @Step("Поиск удалённого пользователя")
    public String searchDeletedPerson() {
        return elementIsVisible(WebTableLocators.NO_ROWS_FOUND).getText();
    }

    @Step("Выбрать до определенного количества строк")
    public List<Integer> selectUpToSomeRows() {
        int[] counts = {10, 20, 30, 40, 50};
        List<Integer> data = new ArrayList<>();

        for (int x : counts) {
            WebElement countRowSelect = elementIsVisible(WebTableLocators.COUNT_ROW_LIST);
            goToElement(countRowSelect);
            new Select(countRowSelect).selectByValue(String.valueOf(x));
            data.add(checkCountRows());
        }
        return data;
    }

    @Step("Проверить количество строк")
    public int checkCountRows() {
        return elementsArePresent(WebTableLocators.FULL_PEOPLE_LIST, 10).size();
    }

    // Первые 6 колонок (без Action), через пробел
    private String rowToString(WebElement row) {
        List<WebElement> cells = row.findElements(WebTableLocators.CELLS);
        List<String> values = new ArrayList<>();
        for (int i = 0; i < Math.min(6, cells.size()); i++) {
            values.add(cells.get(i).getText().trim());
        }
        return String.join(" ", values);
    }
}
