package ui.demoqa.selenium.pages.elements;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ui.demoqa.selenium.common.BasePage;
import ui.demoqa.selenium.locators.elements.LinksLocators;
import ui.demoqa.selenium.models.elements.Links;

import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LinksPage extends BasePage {
    public LinksPage(WebDriver driver, String url) {
        super(driver, url);
    }

    @Step("Открыть ссылку в новой вкладке: {selectLinks}")
    public String openNewTab(Links selectLinks, int timeout) {
        By locator = getNewTabLinkLocators().get(selectLinks);

        // Ждем кликабельности и кликаем
        wait(timeout).until(ExpectedConditions.elementToBeClickable(locator)).click();
        switchToNewWindow(timeout);

        return driver.getTitle();
    }

    public String openNewTab(Links selectLinks) {
        return openNewTab(selectLinks, 10);
    }

    @Step("Получить HTTP-статус ответа для ссылки ({link})")
    public int getLinkResponseStatus(Links link, int timeout) {
        By locator = getResponseLinkLocators().get(link);

        wait(timeout).until(ExpectedConditions.elementToBeClickable(locator)).click();

        WebElement responseElement = elementIsVisible(LinksLocators.RESULT, timeout);
        String responseText = responseElement.getText();

        // Извлечение 3-х значного числа (HTTP статус) регулярным выражением
        Pattern pattern = Pattern.compile("\\b(\\d{3})\\b");
        Matcher matcher = pattern.matcher(responseText);

        if (!matcher.find()) {
            throw new AssertionError("Не удалось извлечь HTTP-статус из ответа: '" + responseText + "'");
        }

        return Integer.parseInt(matcher.group(1));
    }

    public int getLinkResponseStatus(Links link) {
        return getLinkResponseStatus(link, 10);
    }

    public String switchToNewWindow(int timeoutSeconds) {
        String currentWindow = driver.getWindowHandle();
        wait(timeoutSeconds).until(ExpectedConditions.numberOfWindowsToBe(2));
        Set<String> allWindows = driver.getWindowHandles();

        for (String window : allWindows) {
            if (!window.equals(currentWindow)) {
                driver.switchTo().window(window);
                break;
            }
        }
        return currentWindow;
    }

    private Map<Links, By> getNewTabLinkLocators() {
        return Map.of(
                Links.SIMPLE_LINK_LINK, LinksLocators.SIMPLE_LINK_LINK,
                Links.DYNAMIC_LINK_LINK, LinksLocators.DYNAMIC_LINK_LINK
        );
    }

    private Map<Links, By> getResponseLinkLocators() {
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