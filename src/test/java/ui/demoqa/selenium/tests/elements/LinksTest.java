package ui.demoqa.selenium.tests.elements;


import __common.EndpointsDemoqa;
import io.qameta.allure.*;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ui.demoqa.selenium.common.BaseTest;
import ui.demoqa.selenium.models.elements.Links;
import ui.demoqa.selenium.pages.elements.LinksPage;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.params.provider.Arguments.arguments;

@Epic("UI-selenium-test")
@Feature("DemoQA")
@Story("Страница Links")
public class LinksTest extends BaseTest {
    private static final String URL = dotenv.get("DEMOQA_HOST") + EndpointsDemoqa.LINKS.getUrl();

    @ParameterizedTest(name = "Проверка открытия новой вкладки ({0})")
    @MethodSource("provideNewTabTestData")
    @Tags({@Tag("ui"), @Tag("smoke"), @Tag("positive")})
    @Story("Открытие новой вкладки")
    @Description("Проверка корректности Title при открытии ссылки во вкладке")
    public void testOpenNewTabTitle(String testCaseName, Links selectLinks, String expectedTitle) {
        LinksPage linksPage = new LinksPage(driver, URL);
        linksPage.open();

        String result = linksPage.openNewTab(selectLinks);

        Allure.step("Проверить title новой вкладки", () -> {
            assertEquals(expectedTitle, result,
                    String.format("Ожидался title '%s', но получен '%s'", expectedTitle, result));
        });
    }

    private static Stream<Arguments> provideNewTabTestData() {
        return Stream.of(
                arguments("SIMPLE_LINK_LINK", Links.SIMPLE_LINK_LINK, Links.SIMPLE_LINK_LINK.getValue()),
                arguments("DYNAMIC_LINK_LINK", Links.DYNAMIC_LINK_LINK, Links.DYNAMIC_LINK_LINK.getValue())
        );
    }

    @ParameterizedTest(name = "Проверка ответа ссылки {0}")
    @MethodSource("provideLinkResponseTestData")
    @Tags({@Tag("ui"), @Tag("smoke"), @Tag("positive")})
    @Story("Проверка HTTP-статуса ссылок")
    public void testLinkResponseStatus(Links selectLinks, int expectedStatusCode) {
        LinksPage linksPage = new LinksPage(driver, URL);
        linksPage.open();

        int actualStatusCode = linksPage.getLinkResponseStatus(selectLinks);

        Allure.step("Проверить, что ссылка вернула " + expectedStatusCode, () -> {
            assertEquals(expectedStatusCode, actualStatusCode,
                    String.format("Для %s ожидался статус %d, но получен %d",
                            selectLinks.getValue(), expectedStatusCode, actualStatusCode));
        });
    }

    private static Stream<Arguments> provideLinkResponseTestData() {
        return Stream.of(
                arguments(Links.CREATED_LINK, 201),
                arguments(Links.NO_CONTENT_LINK, 204),
                arguments(Links.MOVED_LINK, 301),
                arguments(Links.BAD_REQUEST_LINK, 400),
                arguments(Links.UNAUTHORIZED_LINK, 401),
                arguments(Links.FORBIDDEN_LINK, 403),
                arguments(Links.INVALID_URL_LINK, 404)
        );
    }
}
