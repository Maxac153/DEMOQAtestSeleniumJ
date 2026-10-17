package ui.demoqa.selenium.common;


import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.Objects;

public class BasePage {
    protected final WebDriver driver;
    protected final String url;

    protected BasePage(WebDriver driver, String url) {
        this.driver = driver;
        this.url = url;
    }

    public void open() {
        driver.get(url);
        waitForPageLoaded();
    }

    public void waitForPageLoaded() {
        getWait().until(driver ->
                "complete".equals(((JavascriptExecutor) driver).executeScript("return document.readyState"))
        );
    }

    protected WebDriverWait getWait() {
        return getWait(10);
    }

    protected WebDriverWait getWait(int timeoutSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
    }

    protected WebDriverWait getWait(int timeoutSeconds, long pollMillis) {
        return new WebDriverWait(
                driver,
                Duration.ofSeconds(timeoutSeconds),
                Duration.ofMillis(pollMillis)
        );
    }

    protected WebDriverWait wait(int timeoutSeconds) {
        return new WebDriverWait(driver, Duration.ofSeconds(timeoutSeconds));
    }

    protected WebDriverWait wait(int timeoutSeconds, long pollMillis) {
        return new WebDriverWait(
                driver,
                Duration.ofSeconds(timeoutSeconds),
                Duration.ofMillis(pollMillis)
        );
    }

    public WebElement elementIsVisible(By locator) {
        return elementIsVisible(locator, 10);
    }

    public WebElement elementIsVisible(By locator, int timeoutSeconds) {
        return wait(timeoutSeconds)
                .until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    public List<WebElement> elementsAreVisible(By locator) {
        return elementsAreVisible(locator, 10);
    }

    public List<WebElement> elementsAreVisible(By locator, int timeoutSeconds) {
        return wait(timeoutSeconds)
                .until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }

    public boolean elementIsInvisible(By locator) {
        return elementIsInvisible(locator, 10);
    }

    public boolean elementIsInvisible(By locator, int timeoutSeconds) {
        return wait(timeoutSeconds)
                .until(ExpectedConditions.invisibilityOfElementLocated(locator));
    }

    public void goToElement(WebElement element) {
        ((JavascriptExecutor) driver)
                .executeScript("arguments[0].scrollIntoView(true);", element);
    }

    public WebElement elementIsClickable(By locator) {
        return elementIsClickable(locator, 10);
    }

    public WebElement elementIsClickable(By locator, int timeoutSeconds) {
        return wait(timeoutSeconds)
                .until(ExpectedConditions.elementToBeClickable(locator));
    }

    public WebElement elementIsPresent(By locator) {
        return elementIsPresent(locator, 10);
    }

    public WebElement elementIsPresent(By locator, int timeoutSeconds) {
        return wait(timeoutSeconds)
                .until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public List<WebElement> elementsArePresent(By locator) {
        return elementsArePresent(locator, 10);
    }

    public List<WebElement> elementsArePresent(By locator, int timeoutSeconds) {
        return wait(timeoutSeconds)
                .until(ExpectedConditions.presenceOfAllElementsLocatedBy(locator));
    }

    public WebElement classAttribute(By locator) {
        return elementIsVisible(locator);
    }

    public void actionDragAndDropByOffset(
            WebElement element,
            int xOffset,
            int yOffset
    ) {
        actions()
                .dragAndDropBy(element, xOffset, yOffset)
                .perform();
    }

    public void actionDragAndDropToElement(
            WebElement source,
            WebElement target,
            double pauseBeforeSeconds,
            double pauseAfterSeconds
    ) {
        actions()
                .clickAndHold(source)
                .pause(Duration.ofMillis((long) (pauseBeforeSeconds * 1000)))
                .moveToElement(target)
                .pause(Duration.ofMillis((long) (pauseAfterSeconds * 1000)))
                .release()
                .perform();
    }

    public void actionMoveToElement(WebElement element) {
        actions().moveToElement(element).perform();
    }

    public Actions actions() {
        return new Actions(driver);
    }

    public WebElement childElementIsVisible(
            WebElement parent,
            By childLocator
    ) {
        return childElementIsVisible(parent, childLocator, 10);
    }

    public WebElement childElementIsVisible(
            WebElement parent,
            By childLocator,
            int timeoutSeconds
    ) {
        return wait(timeoutSeconds)
                .until(ExpectedConditions.visibilityOf(
                        parent.findElement(childLocator)
                ));
    }

    public WebElement waitElementPresent(By locator) {
        return elementIsPresent(locator);
    }

    public WebElement waitElementVisible(By locator) {
        return elementIsVisible(locator);
    }

    public List<WebElement> waitElementsPresent(By locator) {
        return elementsArePresent(locator);
    }

    public List<WebElement> waitElementsVisible(By locator) {
        return elementsAreVisible(locator);
    }

    public String waitAttributeChange(
            WebElement element,
            String attribute,
            String oldValue
    ) {
        return waitAttributeChange(element, attribute, oldValue, 10);
    }

    public String waitAttributeChange(
            WebElement element,
            String attribute,
            String oldValue,
            int timeoutSeconds
    ) {
        return wait(timeoutSeconds).until(driver -> {
            String value = element.getDomAttribute(attribute);
            return !Objects.equals(value, oldValue) ? value : null;
        });
    }

    public Alert waitAlertPresent() {
        return waitAlertPresent(10);
    }

    public Alert waitAlertPresent(int timeoutSeconds) {
        return waitAlertPresent(timeoutSeconds, 200);
    }

    public Alert waitAlertPresent(int timeoutSeconds, long pollMillis) {
        return wait(timeoutSeconds, pollMillis)
                .until(ExpectedConditions.alertIsPresent());
    }

    public String waitNewWindow(String currentWindow) {
        return waitNewWindow(currentWindow, 10);
    }

    public String waitNewWindow(String currentWindow, int timeoutSeconds) {
        return wait(timeoutSeconds).until(driver -> Objects.requireNonNull(driver.getWindowHandles().stream()
                .filter(handle -> !handle.equals(currentWindow))
                .findFirst()
                .orElse(null)));
    }

    public String switchToNewWindow() {
        return switchToNewWindow(10);
    }

    public String switchToNewWindow(int timeoutSeconds) {
        String currentWindow = driver.getWindowHandle();
        String newWindow = waitNewWindow(currentWindow, timeoutSeconds);

        driver.switchTo().window(newWindow);
        return newWindow;
    }

    public WebElement elementIsEnabled(By locator) {
        return elementIsEnabled(locator, 10);
    }

    public WebElement elementIsEnabled(By locator, int timeoutSeconds) {
        return wait(timeoutSeconds).until(driver -> {
            WebElement element = driver.findElement(locator);
            return element.isEnabled() ? element : null;
        });
    }

    public WebElement waitAttributeChanged(
            By locator,
            String attribute,
            String initialValue
    ) {
        return waitAttributeChanged(locator, attribute, initialValue, 10);
    }

    public WebElement waitAttributeChanged(
            By locator,
            String attribute,
            String initialValue,
            int timeoutSeconds
    ) {
        return wait(timeoutSeconds).until(driver -> {
            WebElement element = driver.findElement(locator);
            String value = element.getDomAttribute(attribute);

            return !Objects.equals(value, initialValue) ? element : null;
        });
    }

    public String getCssProperty(WebElement element, String propertyName) {
        return element.getCssValue(propertyName);
    }

    public String waitCssPropertyEquals(
            WebElement element,
            String propertyName,
            String expectedValue
    ) {
        return waitCssPropertyEquals(element, propertyName, expectedValue, 10);
    }

    public String waitCssPropertyEquals(
            WebElement element,
            String propertyName,
            String expectedValue,
            int timeoutSeconds
    ) {
        return wait(timeoutSeconds).until(driver -> {
            String actualValue = getCssProperty(element, propertyName);
            return Objects.equals(actualValue, expectedValue) ? actualValue : null;
        });
    }

    public String waitCssPropertyNotEquals(
            WebElement element,
            String propertyName,
            String unexpectedValue
    ) {
        return waitCssPropertyNotEquals(
                element,
                propertyName,
                unexpectedValue,
                10
        );
    }

    public String waitCssPropertyNotEquals(
            WebElement element,
            String propertyName,
            String unexpectedValue,
            int timeoutSeconds
    ) {
        return wait(timeoutSeconds).until(driver -> {
            String actualValue = getCssProperty(element, propertyName);
            return !Objects.equals(actualValue, unexpectedValue)
                    ? actualValue
                    : null;
        });
    }

    public void waitForAttributeValue(
            By locator,
            String attribute,
            String expectedValue
    ) {
        waitForAttributeValue(locator, attribute, expectedValue, 60);
    }

    public void waitForAttributeValue(
            By locator,
            String attribute,
            String expectedValue,
            int timeoutSeconds
    ) {
        wait(timeoutSeconds).until((ExpectedCondition<Boolean>) driver -> {
            String actualValue = driver
                    .findElement(locator)
                    .getDomAttribute(attribute);

            return Objects.equals(actualValue, expectedValue);
        });
    }
}
