package ui.demoqa.playwright.pages.elements;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import ui.demoqa.playwright.common.BasePage;
import ui.demoqa.playwright.locators.elements.WebTableLocators;
import ui.demoqa.playwright.models.elements.Person;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class WebTablePage extends BasePage {

    public WebTablePage(Page page, String url) {
        super(page, url);
    }

    @Step("Создание нового пользователя")
    public List<String> addNewPerson(Person person) {
        elementIsVisible(WebTableLocators.ADD_BUTTON).click();
        elementIsVisible(WebTableLocators.FIRSTNAME_INPUT).fill(person.firstname());
        elementIsVisible(WebTableLocators.LASTNAME_INPUT).fill(person.lastname());
        elementIsVisible(WebTableLocators.EMAIL_INPUT).fill(person.email());
        elementIsVisible(WebTableLocators.AGE_INPUT).fill(String.valueOf(person.age()));
        elementIsVisible(WebTableLocators.SALARY_INPUT).fill(String.valueOf(person.salary()));
        elementIsVisible(WebTableLocators.DEPARTMENT_INPUT).fill(person.department());
        elementIsVisible(WebTableLocators.SUBMIT).click();

        // Ожидание исчезновения формы
        elementIsInvisible(WebTableLocators.REGISTRATION_FORM, 10);

        return List.of(person.firstname(), person.lastname(), String.valueOf(person.age()),
                person.email(), String.valueOf(person.salary()), person.department());
    }

    @Step("Получение всех пользователей")
    public List<String> getPersons() {
        List<Locator> peopleList = elementsAreVisible(WebTableLocators.FULL_PEOPLE_LIST);
        return peopleList.stream()
                .map(item -> item.innerText().replace("\n", " "))
                .collect(Collectors.toList());
    }

    @Step("Поиск пользователя")
    public void searchSomePerson(String keyWord) {
        elementIsVisible(WebTableLocators.SEARCH_INPUT).fill(keyWord);
    }

    @Step("Поиск пользователя")
    public String getSearchPerson() {
        // Явно ждем, что строка таблицы с результатами поиска стала видимой
        Locator row = elementIsVisible("xpath=//div[contains(@class, 'rt-tr') and .//span[@title='Delete']]");
        return row.innerText();
    }

    @Step("Обновить личные данные")
    public String updatePersonInfo(String age) {
        elementIsVisible(WebTableLocators.UPDATE_BUTTON).click();
        elementIsVisible(WebTableLocators.AGE_INPUT).fill(age);
        elementIsVisible(WebTableLocators.SUBMIT).click();
        return age;
    }

    @Step("Удалить пользователя")
    public void deletePerson() {
        elementIsVisible(WebTableLocators.DELETE_BUTTON).click();
    }

    @Step("Поиск удалённого пользователя")
    public String searchDeletedPerson() {
        return elementIsVisible(WebTableLocators.NO_ROWS_FOUND).innerText();
    }

    @Step("Выбрать до определенного количества строк")
    public List<Integer> selectUpToSomeRows() {
        int[] counts = {10, 20, 30, 40, 50};
        List<Integer> data = new ArrayList<>();

        for (int x : counts) {
            Locator countRowSelect = elementIsVisible(WebTableLocators.COUNT_ROW_LIST);
            goToElement(countRowSelect);
            countRowSelect.selectOption(String.valueOf(x));
            data.add(checkCountRows());
        }
        return data;
    }

    @Step("Проверить количество строк")
    public int checkCountRows() {
        return elementsArePresent(WebTableLocators.FULL_PEOPLE_LIST, 10).size();
    }
}
