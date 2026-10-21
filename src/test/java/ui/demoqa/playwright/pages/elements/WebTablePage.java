package ui.demoqa.playwright.pages.elements;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import ui.demoqa.playwright.common.BasePage;
import ui.demoqa.playwright.locators.elements.WebTableLocators;
import ui.demoqa.playwright.models.elements.Person;

import java.util.ArrayList;
import java.util.List;

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

    @Step("Получить список пользователей")
    public List<String> getPersons() {
        Locator rows = page.locator(WebTableLocators.ROWS);
        rows.first().waitFor();

        List<String> result = new ArrayList<>();
        for (int i = 0; i < rows.count(); i++) {
            List<String> cells = rows.nth(i).locator("td").allInnerTexts();
            result.add(String.join(" ", cells.subList(0, 6)).trim());
        }
        return result;
    }

    @Step("Поиск пользователя")
    public void searchSomePerson(String keyWord) {
        elementIsVisible(WebTableLocators.SEARCH_INPUT).fill(keyWord);
    }

    @Step("Получить строку пользователя")
    public String getSearchPerson(String email) {
        Locator row = page.locator(WebTableLocators.ROW_WITH_DELETE)
                .filter(new Locator.FilterOptions().setHasText(email))
                .first();
        row.waitFor(new Locator.WaitForOptions().setTimeout(10000));
        return row.innerText();
    }

    @Step("Обновить личные данные")
    public String updatePersonInfo(String age) {
        elementIsVisible(WebTableLocators.UPDATE_BUTTON).click();
        Locator ageInput = elementIsVisible(WebTableLocators.AGE_INPUT);
        ageInput.fill(age);
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
