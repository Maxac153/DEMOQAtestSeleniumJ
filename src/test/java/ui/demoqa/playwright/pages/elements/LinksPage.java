package ui.demoqa.playwright.pages.elements;

import com.microsoft.playwright.Page;
import io.qameta.allure.Step;
import ui.demoqa.playwright.common.BasePage;
import ui.demoqa.playwright.locators.elements.LinksLocators;
import ui.demoqa.playwright.models.elements.Links;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LinksPage extends BasePage {
    private static final Pattern STATUS_PATTERN = Pattern.compile("\\b(\\d{3})\\b");

    public LinksPage(Page page, String url) {
        super(page, url);
    }

    @Step("Открыть ссылку в новой вкладке: {selectLinks}")
    public String openNewTab(Links selectLinks, int timeout) {
        String selector = getNewTabLinkLocators().get(selectLinks);

        // Ждем кликабельности, кликаем и получаем новую вкладку
        Page newPage = waitNewPage(() -> elementIsClickable(selector, timeout).click());
        return newPage.title();
    }

    public String openNewTab(Links selectLinks) {
        return openNewTab(selectLinks, 10);
    }

    @Step("Получить HTTP-статус ответа для ссылки ({link})")
    public int getLinkResponseStatus(Links link, int timeout) {
        String selector = getResponseLinkLocators().get(link);
        elementIsClickable(selector, timeout).click();
        String responseText = elementIsVisible(LinksLocators.RESULT, timeout).innerText();

        // Извлечение 3-х значного числа (HTTP статус) регулярным выражением
        Matcher matcher = STATUS_PATTERN.matcher(responseText);

        if (!matcher.find()) {
            throw new AssertionError("Не удалось извлечь HTTP-статус из ответа: '" + responseText + "'");
        }

        return Integer.parseInt(matcher.group(1));
    }

    public int getLinkResponseStatus(Links link) {
        return getLinkResponseStatus(link, 10);
    }

    private Map<Links, String> getNewTabLinkLocators() {
        return Map.of(
                Links.SIMPLE_LINK_LINK, LinksLocators.SIMPLE_LINK_LINK,
                Links.DYNAMIC_LINK_LINK, LinksLocators.DYNAMIC_LINK_LINK
        );
    }

    private Map<Links, String> getResponseLinkLocators() {
        return Map.of(
                Links.CREATED_LINK, LinksLocators.CREATED_LINK,
                Links.NO_CONTENT_LINK, LinksLocators.NO_CONTENT_LINK,
                Links.MOVED_LINK, LinksLocators.MOVED_LINK,
                Links.BAD_REQUEST_LINK, LinksLocators.BAD_REQUEST_LINK,
                Links.UNAUTHORIZED_LINK, LinksLocators.UNAUTHORIZED_LINK,
                Links.FORBIDDEN_LINK, LinksLocators.FORBIDDEN_LINK,
                Links.INVALID_URL_LINK, LinksLocators.INVALID_URL_LINK
        );
    }
}