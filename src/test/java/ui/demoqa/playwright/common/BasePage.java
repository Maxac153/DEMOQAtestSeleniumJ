package ui.demoqa.playwright.common;

import com.microsoft.playwright.*;
import com.microsoft.playwright.options.BoundingBox;
import com.microsoft.playwright.options.LoadState;
import com.microsoft.playwright.options.WaitForSelectorState;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.BooleanSupplier;

public class BasePage {
    private static final int DEFAULT_TIMEOUT = 10;
    private static final long DEFAULT_POLL_MILLIS = 100;

    protected final Page page;
    protected final String url;

    protected BasePage(Page page, String url) {
        this.page = page;
        this.url = url;
    }

    public Page getPage() {
        return page;
    }

    public void open() {
        page.navigate(url);
        waitForPageLoaded();
    }

    public void waitForPageLoaded() {
        page.waitForLoadState(LoadState.LOAD);
    }

    public Locator elementIsVisible(String selector) {
        return elementIsVisible(selector, DEFAULT_TIMEOUT);
    }

    public Locator elementIsVisible(String selector, int timeoutSeconds) {
        return waitForState(selector, WaitForSelectorState.VISIBLE, timeoutSeconds);
    }

    public List<Locator> elementsAreVisible(String selector) {
        return elementsAreVisible(selector, DEFAULT_TIMEOUT);
    }

    public List<Locator> elementsAreVisible(String selector, int timeoutSeconds) {
        Locator locator = page.locator(selector);
        waitUntil(() -> {
            if (locator.count() == 0) {
                return false;
            }
            for (Locator item : locator.all()) {
                if (!item.isVisible()) {
                    return false;
                }
            }
            return true;
        }, timeoutSeconds, DEFAULT_POLL_MILLIS, "Не все элементы видимы: " + selector);
        return locator.all();
    }

    public boolean elementIsInvisible(String selector) {
        return elementIsInvisible(selector, DEFAULT_TIMEOUT);
    }

    public boolean elementIsInvisible(String selector, int timeoutSeconds) {
        waitForState(selector, WaitForSelectorState.HIDDEN, timeoutSeconds);
        return true;
    }

    public Locator elementIsClickable(String selector) {
        return elementIsClickable(selector, DEFAULT_TIMEOUT);
    }

    public Locator elementIsClickable(String selector, int timeoutSeconds) {
        Locator locator = page.locator(selector).first();
        waitUntil(() -> locator.count() > 0 && locator.isVisible() && locator.isEnabled(),
                timeoutSeconds, DEFAULT_POLL_MILLIS, "Элемент не кликабелен: " + selector);
        return locator;
    }

    public Locator elementIsPresent(String selector) {
        return elementIsPresent(selector, DEFAULT_TIMEOUT);
    }

    public Locator elementIsPresent(String selector, int timeoutSeconds) {
        return waitForState(selector, WaitForSelectorState.ATTACHED, timeoutSeconds);
    }

    public List<Locator> elementsArePresent(String selector) {
        return elementsArePresent(selector, DEFAULT_TIMEOUT);
    }

    public List<Locator> elementsArePresent(String selector, int timeoutSeconds) {
        Locator locator = page.locator(selector);
        waitUntil(() -> locator.count() > 0,
                timeoutSeconds, DEFAULT_POLL_MILLIS, "Элементы не найдены: " + selector);
        return locator.all();
    }

    public Locator elementIsEnabled(String selector) {
        return elementIsEnabled(selector, DEFAULT_TIMEOUT);
    }

    public Locator elementIsEnabled(String selector, int timeoutSeconds) {
        Locator locator = page.locator(selector).first();
        waitUntil(() -> locator.count() > 0 && locator.isEnabled(),
                timeoutSeconds, DEFAULT_POLL_MILLIS, "Элемент не активен: " + selector);
        return locator;
    }

    public Locator classAttribute(String selector) {
        return elementIsVisible(selector);
    }

    public Locator childElementIsVisible(Locator parent, String childSelector) {
        return childElementIsVisible(parent, childSelector, DEFAULT_TIMEOUT);
    }

    public Locator childElementIsVisible(Locator parent, String childSelector, int timeoutSeconds) {
        Locator child = parent.locator(childSelector).first();
        child.waitFor(new Locator.WaitForOptions()
                .setState(WaitForSelectorState.VISIBLE)
                .setTimeout(toMillis(timeoutSeconds)));
        return child;
    }

    public Locator waitElementPresent(String selector) {
        return elementIsPresent(selector);
    }

    public Locator waitElementVisible(String selector) {
        return elementIsVisible(selector);
    }

    public List<Locator> waitElementsPresent(String selector) {
        return elementsArePresent(selector);
    }

    public List<Locator> waitElementsVisible(String selector) {
        return elementsAreVisible(selector);
    }

    public void goToElement(Locator element) {
        element.scrollIntoViewIfNeeded();
    }

    public void actionMoveToElement(Locator element) {
        element.hover();
    }

    public void actionDragAndDropByOffset(Locator element, int xOffset, int yOffset) {
        element.scrollIntoViewIfNeeded();
        BoundingBox box = element.boundingBox();
        double startX = box.x + box.width / 2;
        double startY = box.y + box.height / 2;

        page.mouse().move(startX, startY);
        page.mouse().down();
        page.mouse().move(startX + xOffset, startY + yOffset, new Mouse.MoveOptions().setSteps(10));
        page.mouse().up();
    }

    public void actionDragAndDropToElement(
            Locator source,
            Locator target,
            double pauseBeforeSeconds,
            double pauseAfterSeconds
    ) {
        source.scrollIntoViewIfNeeded();
        source.hover();
        page.mouse().down();
        page.waitForTimeout(pauseBeforeSeconds * 1000);

        BoundingBox box = target.boundingBox();
        page.mouse().move(box.x + box.width / 2, box.y + box.height / 2, new Mouse.MoveOptions().setSteps(10));
        page.waitForTimeout(pauseAfterSeconds * 1000);
        page.mouse().up();
    }

    public String waitAttributeChange(Locator element, String attribute, String oldValue) {
        return waitAttributeChange(element, attribute, oldValue, DEFAULT_TIMEOUT);
    }

    public String waitAttributeChange(
            Locator element,
            String attribute,
            String oldValue,
            int timeoutSeconds
    ) {
        AtomicReference<String> result = new AtomicReference<>();
        waitUntil(() -> {
                    String value = getAttribute(element, attribute);
                    if (!Objects.equals(value, oldValue)) {
                        result.set(value);
                        return true;
                    }
                    return false;
                }, timeoutSeconds, DEFAULT_POLL_MILLIS,
                "Атрибут '" + attribute + "' не изменился");
        return result.get();
    }

    public Locator waitAttributeChanged(String selector, String attribute, String initialValue) {
        return waitAttributeChanged(selector, attribute, initialValue, DEFAULT_TIMEOUT);
    }

    public Locator waitAttributeChanged(
            String selector,
            String attribute,
            String initialValue,
            int timeoutSeconds
    ) {
        Locator locator = page.locator(selector).first();
        waitUntil(() -> !Objects.equals(getAttribute(locator, attribute), initialValue),
                timeoutSeconds, DEFAULT_POLL_MILLIS,
                "Атрибут '" + attribute + "' не изменился: " + selector);
        return locator;
    }

    public void waitForAttributeValue(String selector, String attribute, String expectedValue) {
        waitForAttributeValue(selector, attribute, expectedValue, 60);
    }

    public void waitForAttributeValue(
            String selector,
            String attribute,
            String expectedValue,
            int timeoutSeconds
    ) {
        Locator locator = page.locator(selector).first();
        waitUntil(() -> Objects.equals(getAttribute(locator, attribute), expectedValue),
                timeoutSeconds, DEFAULT_POLL_MILLIS,
                "Атрибут '" + attribute + "' не стал равен '" + expectedValue + "'");
    }

    public String getCssProperty(Locator element, String propertyName) {
        return (String) element.evaluate(
                "(el, prop) => getComputedStyle(el).getPropertyValue(prop)",
                propertyName
        );
    }

    public String waitCssPropertyEquals(Locator element, String propertyName, String expectedValue) {
        return waitCssPropertyEquals(element, propertyName, expectedValue, DEFAULT_TIMEOUT);
    }

    public String waitCssPropertyEquals(
            Locator element,
            String propertyName,
            String expectedValue,
            int timeoutSeconds
    ) {
        waitUntil(() -> Objects.equals(getCssProperty(element, propertyName), expectedValue),
                timeoutSeconds, DEFAULT_POLL_MILLIS,
                "CSS '" + propertyName + "' не стал равен '" + expectedValue + "'");
        return getCssProperty(element, propertyName);
    }

    public String waitCssPropertyNotEquals(Locator element, String propertyName, String unexpectedValue) {
        return waitCssPropertyNotEquals(element, propertyName, unexpectedValue, DEFAULT_TIMEOUT);
    }

    public String waitCssPropertyNotEquals(
            Locator element,
            String propertyName,
            String unexpectedValue,
            int timeoutSeconds
    ) {
        waitUntil(() -> !Objects.equals(getCssProperty(element, propertyName), unexpectedValue),
                timeoutSeconds, DEFAULT_POLL_MILLIS,
                "CSS '" + propertyName + "' остался равен '" + unexpectedValue + "'");
        return getCssProperty(element, propertyName);
    }

    public String acceptDialog(Runnable action) {
        return handleDialog(action, true, null);
    }

    public String acceptDialog(Runnable action, String promptText) {
        return handleDialog(action, true, promptText);
    }

    public String dismissDialog(Runnable action) {
        return handleDialog(action, false, null);
    }

    private String handleDialog(Runnable action, boolean accept, String promptText) {
        AtomicReference<String> message = new AtomicReference<>();
        page.onceDialog(dialog -> {
            message.set(dialog.message());
            if (accept) {
                if (promptText != null) {
                    dialog.accept(promptText);
                } else {
                    dialog.accept();
                }
            } else {
                dialog.dismiss();
            }
        });
        action.run();
        waitUntil(() -> message.get() != null, 10, DEFAULT_POLL_MILLIS, "Диалог не появился");
        return message.get();
    }

    public Page waitNewPage(Runnable action) {
        Page newPage = page.context().waitForPage(action);
        newPage.waitForLoadState(LoadState.LOAD);
        return newPage;
    }

    private Locator waitForState(String selector, WaitForSelectorState state, int timeoutSeconds) {
        Locator locator = page.locator(selector).first();
        locator.waitFor(new Locator.WaitForOptions()
                .setState(state)
                .setTimeout(toMillis(timeoutSeconds)));
        return locator;
    }

    private String getAttribute(Locator locator, String attribute) {
        if (locator.count() == 0) {
            return null;
        }
        return locator.first().getAttribute(
                attribute,
                new Locator.GetAttributeOptions().setTimeout(1000)
        );
    }

    private void waitUntil(
            BooleanSupplier condition,
            int timeoutSeconds,
            long pollMillis,
            String message
    ) {
        long deadline = System.nanoTime() + TimeUnit.SECONDS.toNanos(timeoutSeconds);
        while (true) {
            try {
                if (condition.getAsBoolean()) {
                    return;
                }
            } catch (PlaywrightException ignored) {
                // элемент мог не успеть появиться или перерисоваться, пробуем ещё раз
            }
            if (System.nanoTime() >= deadline) {
                throw new TimeoutError(message + " (таймаут " + timeoutSeconds + " с)");
            }
            page.waitForTimeout(pollMillis);
        }
    }

    private static double toMillis(int seconds) {
        return seconds * 1000.0;
    }
}
