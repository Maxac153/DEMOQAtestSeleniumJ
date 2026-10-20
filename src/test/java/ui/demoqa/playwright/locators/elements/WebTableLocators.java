package ui.demoqa.playwright.locators.elements;

public class WebTableLocators {
    // Add person form
    public static final String REGISTRATION_FORM = "xpath=//div[@class='modal-dialog modal-lg']";
    public static final String ADD_BUTTON = "button[id='addNewRecordButton']";
    public static final String FIRSTNAME_INPUT = "input[id='firstName']";
    public static final String LASTNAME_INPUT = "input[id='lastName']";
    public static final String EMAIL_INPUT = "input[id='userEmail']";
    public static final String AGE_INPUT = "input[id='age']";
    public static final String SALARY_INPUT = "input[id='salary']";
    public static final String DEPARTMENT_INPUT = "input[id='department']";
    public static final String SUBMIT = "button[id='submit']";

    // Table
    public static final String FULL_PEOPLE_LIST = "div.rt-tbody div.rt-tr-group";
    public static final String SEARCH_INPUT = "input[id='searchBox']";
    public static final String DELETE_BUTTON = "span[title='Delete']";
    public static final String UPDATE_BUTTON = "span[title='Edit']";
    public static final String ROW_PARENT = "xpath=./ancestor::div[contains(@class, 'rt-tr')]";
    public static final String NO_ROWS_FOUND = "div.rt-noData";
    public static final String COUNT_ROW_LIST = "select[aria-label='rows per page']";
}
