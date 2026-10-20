package ui.demoqa.playwright.tests.elements;

import __common.EndpointsDemoqa;
import io.qameta.allure.*;
import net.datafaker.Faker;
import org.junit.jupiter.api.BeforeEach;
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
@Tags({
        @Tag("ui"),
        @Tag("smoke"),
        @Tag("positive")
})
public class WebTableTest extends BaseTest {
    private static final String URL = dotenv.get("DEMOQA_HOST") + EndpointsDemoqa.WEB_TABLES.getUrl();
    private Faker fake;
    private WebTablePage webTablePage;

    @BeforeEach
    public void setUpWebTable() {
        fake = new Faker();
        webTablePage = new WebTablePage(page, URL);
        webTablePage.open();
    }

    private Person createPerson() {
        String firstName = fake.name().firstName();
        String lastName = fake.name().lastName();
        return new Person(
                firstName + " " + lastName + " " + fake.name().fullName(),
                firstName,
                lastName,
                fake.number().numberBetween(10, 80),
                fake.number().numberBetween(10000, 100000),
                fake.job().field(),
                fake.internet().emailAddress(),
                fake.address().fullAddress(),
                fake.address().fullAddress(),
                fake.phoneNumber().cellPhone()
        );
    }

    @Test
    @Story("Добавление пользователя")
    @Description("Проверка добавления пользователя")
    public void testAddNewPerson() {
        Person person = createPerson();
        List<String> newPerson = webTablePage.addNewPerson(person);
        List<String> tablePersons = webTablePage.getPersons();
        String joinedPersonString = String.join(" ", newPerson);
        Allure.step(
                "Проверить, что пользователь добавлен в таблицу",
                () -> assertTrue(
                        tablePersons.contains(joinedPersonString),
                        String.format(
                                "Пользователь %s не был добавлен в таблицу",
                                person.email()
                        )
                )
        );
    }

    @Test
    @Story("Поиск пользователя")
    @Description("Проверка поиска пользователя по email")
    public void testWebTableSearchPerson() {
        Person person = createPerson();
        webTablePage.addNewPerson(person);
        webTablePage.searchSomePerson(person.email());
        String searchResult = webTablePage.getSearchPerson();
        Allure.step(
                "Проверить, что пользователь найден по email",
                () -> assertTrue(
                        searchResult.contains(person.email()),
                        String.format(
                                "Пользователь с email %s не был найден",
                                person.email()
                        )
                )
        );
    }

    @Test
    @Story("Обновление пользователя")
    @Description("Проверка обновления возраста пользователя")
    public void testWebTableUpdatePersonInfo() {
        Person person = createPerson();
        String newAge = person.age() == 20 ? "21" : "20";
        webTablePage.addNewPerson(person);
        webTablePage.searchSomePerson(person.email());
        String updatedAge = webTablePage.updatePersonInfo(newAge);
        String searchResult = webTablePage.getSearchPerson();
        Allure.step(
                "Проверить, что возраст пользователя обновился",
                () -> assertTrue(
                        searchResult.contains(updatedAge),
                        String.format(
                                "Возраст %s не появился в строке пользователя %s",
                                updatedAge,
                                person.email()
                        )
                )
        );
    }

    @Test
    @Story("Удаление пользователя")
    @Description("Проверка удаления пользователя")
    public void testWebTableDeletePerson() {
        Person person = createPerson();
        webTablePage.addNewPerson(person);
        webTablePage.searchSomePerson(person.email());
        webTablePage.deletePerson();
        String searchResult = webTablePage.searchDeletedPerson();
        Allure.step(
                "Проверить, что пользователь удалён",
                () -> assertEquals(
                        "No rows found",
                        searchResult,
                        String.format(
                                "Пользователь с email %s не был удалён",
                                person.email()
                        )
                )
        );
    }

    @Test
    @Story("Размер страницы таблицы")
    @Description("Проверка количества строк при переключении размера страницы")
    public void testWebTableChangeCountRow() {
        List<Integer> expectedRowCounts = List.of(10, 20, 30, 40, 50);
        List<Integer> actualRowCounts = webTablePage.selectUpToSomeRows();
        Allure.step(
                "Проверить количество строк для каждого размера страницы",
                () -> assertEquals(
                        expectedRowCounts,
                        actualRowCounts,
                        String.format(
                                "Ожидались количества строк %s, но получены %s",
                                expectedRowCounts,
                                actualRowCounts
                        )
                )
        );
    }
}