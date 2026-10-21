package ui.demoqa.selenium.locators.elements;

import org.openqa.selenium.By;

public class WebTableLocators {
    // Add person form
    public static final By REGISTRATION_FORM = By.xpath("//div[@class='modal-dialog modal-lg']");
    public static final By ADD_BUTTON = By.cssSelector("button[id='addNewRecordButton']");
    public static final By FIRSTNAME_INPUT = By.cssSelector("input[id='firstName']");
    public static final By LASTNAME_INPUT = By.cssSelector("input[id='lastName']");
    public static final By EMAIL_INPUT = By.cssSelector("input[id='userEmail']");
    public static final By AGE_INPUT = By.cssSelector("input[id='age']");
    public static final By SALARY_INPUT = By.cssSelector("input[id='salary']");
    public static final By DEPARTMENT_INPUT = By.cssSelector("input[id='department']");
    public static final By SUBMIT = By.cssSelector("button[id='submit']");

    // Table
    public static final By FULL_PEOPLE_LIST = By.cssSelector("table tbody tr");
    public static final By ROWS = By.cssSelector("table tbody tr:has(span[id^='delete-record'])");
    public static final By CELLS = By.tagName("td");
    public static final By SEARCH_INPUT = By.cssSelector("input[id='searchBox']");
    public static final By DELETE_BUTTON = By.cssSelector("span[title='Delete']");
    public static final By UPDATE_BUTTON = By.cssSelector("span[title='Edit']");
    public static final By NO_ROWS_FOUND = By.xpath("//strong");
    public static final By COUNT_ROW_LIST = By.cssSelector("select[aria-label='rows per page']");

    // Строка с кнопкой Delete, содержащая email
    public static By rowWithEmail(String email) {
        return By.xpath("//tbody//tr[.//span[@title='Delete'] and contains(., '" + email + "')]");
    }
}