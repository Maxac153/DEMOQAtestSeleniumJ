package ui.demoqa.playwright.tests.elements;

import __common.EndpointsDemoqa;
import __common.TestFaker;
import io.qameta.allure.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;
import ui.demoqa.playwright.common.BaseTest;
import ui.demoqa.playwright.models.elements.Person;
import ui.demoqa.playwright.pages.elements.WebTablePage;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Epic("UI-playwright-test")
@Feature("DemoQA")
@Story("Страница Web Tables")
public class WebTableTest extends BaseTest {
    private static final String URL = dotenv.get("DEMOQA_HOST") + EndpointsDemoqa.WEB_TABLES.getUrl();

    private Person createPerson() {
        String firstName = TestFaker.get().name().firstName();
        String lastName = TestFaker.get().name().lastName();

        return new Person(
                firstName + " " + lastName + " " + TestFaker.get().name().fullName(),
                firstName,
                lastName,
                TestFaker.get().number().numberBetween(10, 80),
                TestFaker.get().number().numberBetween(10000, 100000),
                TestFaker.get().job().field(),
                TestFaker.get().internet().emailAddress(),
                TestFaker.get().address().fullAddress(),
                TestFaker.get().address().fullAddress(),
                TestFaker.get().phoneNumber().cellPhone()
        );
    }

    @Test
    @Tags({@Tag("ui"), @Tag("smoke"), @Tag("positive")})
    @Story("Добавление пользователя")
    @Description("Проверка добавления пользователя")
    public void testAddNewPerson() {
        Person person = createPerson();

        WebTablePage webTablePage = new WebTablePage(page, URL);
        webTablePage.open();

        List<String> newPerson = webTablePage.addNewPerson(person);
        List<String> tablePersons = webTablePage.getPersons();

        String joinedPersonString = String.join(" ", newPerson);

        Allure.step("Проверить, что пользователь добавлен в таблицу", () -> assertTrue(tablePersons.contains(joinedPersonString),
                String.format("Пользователь %s не был добавлен в таблицу", person.email())));
    }

    @Test
    @Tags({@Tag("ui"), @Tag("smoke"), @Tag("positive")})
    @Story("Поиск пользователя")
    @Description("Проверка поиска пользователя по email")
    public void testWebTableSearchPerson() {
        Person person = createPerson();

        WebTablePage webTablePage = new WebTablePage(page, URL);
        webTablePage.open();

        webTablePage.addNewPerson(person);
        webTablePage.searchSomePerson(person.email());

        String searchResult = webTablePage.getSearchPerson(person.email());

        Allure.step("Проверить, что пользователь найден по email", () -> assertTrue(searchResult.contains(person.email()),
                String.format("Пользователь с email %s не был найден", person.email())));
    }

    @Test
    @Tags({@Tag("ui"), @Tag("smoke"), @Tag("positive")})
    @Story("Обновление пользователя")
    @Description("Проверка обновления возраста пользователя")
    public void testWebTableUpdatePersonInfo() {
        Person person = createPerson();

        WebTablePage webTablePage = new WebTablePage(page, URL);
        webTablePage.open();

        webTablePage.addNewPerson(person);
        webTablePage.searchSomePerson(person.email());

        String updatedAge = webTablePage.updatePersonInfo("20");
        String searchResult = webTablePage.getSearchPerson(person.email());

        Allure.step("Проверить, что возраст пользователя обновился", () -> assertTrue(searchResult.contains(updatedAge),
                String.format("Возраст %s не появился в строке пользователя", updatedAge)));
    }

    @Test
    @Tags({@Tag("ui"), @Tag("smoke"), @Tag("positive")})
    @Story("Удаление пользователя")
    @Description("Проверка удаления пользователя")
    public void testWebTableDeletePerson() {
        Person person = createPerson();

        WebTablePage webTablePage = new WebTablePage(page, URL);
        webTablePage.open();

        webTablePage.addNewPerson(person);
        webTablePage.searchSomePerson(person.email());
        webTablePage.deletePerson();

        String searchResult = webTablePage.searchDeletedPerson();

        Allure.step("Проверить, что пользователь удалён", () -> assertEquals("1 of 0", searchResult,
                String.format("Пользователь с email %s не был удалён", person.email())));
    }

    @Test
    @Tags({@Tag("ui"), @Tag("smoke"), @Tag("positive")})
    @Story("Размер страницы таблицы")
    @Description("Проверка доступных размеров страницы таблицы")
    public void testWebTableChangeCountRow() {
        WebTablePage webTablePage = new WebTablePage(page, URL);
        webTablePage.open();

        List<Integer> availableRowCounts = webTablePage.selectUpToSomeRows();
        List<Integer> expectedRowCounts = List.of(10, 20, 30, 40, 50);

        Allure.step("Проверить доступные размеры страницы", () -> assertEquals(expectedRowCounts, availableRowCounts,
                String.format("Ожидались размеры страницы %s, но получены %s", expectedRowCounts, availableRowCounts)));
    }
}