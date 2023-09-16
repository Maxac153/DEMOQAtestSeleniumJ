package locators;

import org.openqa.selenium.By;

public class SelectMenuLocators {
    public static final By SELECTS = By.xpath("//div[@class=' css-1hwfws3']");

    // Select Value
    public static final By SELECT_GROUP_ONE_OPTION_ONE = By.xpath("//div[@id='react-select-2-option-0-0']");
    public static final By SELECT_GROUP_ONE_OPTION_TWO = By.xpath("//div[@id='react-select-2-option-0-1']");
    public static final By SELECT_GROUP_TWO_OPTION_ONE = By.xpath("//div[@id='react-select-2-option-1-0']");
    public static final By SELECT_GROUP_TWO_OPTION_TWO = By.xpath("//div[@id='react-select-2-option-1-1']");
    public static final By SELECT_A_ROOT_OPTION = By.xpath("//div[@id='react-select-2-option-2']");
    public static final By SELECT_ANOTHER_ROOT_OPTION = By.xpath("//div[@id='react-select-2-option-3']");

    // Select One
    public static final By SELECT_DR = By.xpath("//div[@id='react-select-3-option-0-0']");
    public static final By SELECT_MR = By.xpath("//div[@id='react-select-3-option-0-1']");
    public static final By SELECT_MRS = By.xpath("//div[@id='react-select-3-option-0-2']");
    public static final By SELECT_MS = By.xpath("//div[@id='react-select-3-option-0-3']");
    public static final By SELECT_PROF = By.xpath("//div[@id='react-select-3-option-0-4']");
    public static final By SELECT_OTHER = By.xpath("//div[@id='react-select-3-option-0-5']");

    // Old Style Select Menu

    public static final By OLD_STYLE_SELECT = By.xpath("//select[@id='oldSelectMenu']");

    // Multiselect drop down
    public static final By SELECT_GREEN = By.xpath("//div[@id='react-select-4-option-0']");
    public static final By SELECT_BLUE = By.xpath("//div[@id='react-select-4-option-1']");
    public static final By SELECT_BLACK = By.xpath("//div[@id='react-select-4-option-2']");
    public static final By SELECT_RED = By.xpath("//div[@id='react-select-4-option-3']");

    public static final By REMOVE_GREEN = By.xpath("//div[text()='Green']/../div");
    public static final By REMOVE_BLUE = By.xpath("//div[text()='Blue']/../div");
    public static final By REMOVE_BLACK = By.xpath("//div[text()='Black']/../div");
    public static final By REMOVE_RED = By.xpath("//div[text()='Red']/../div");
    public static final By REMOVE_ALL_COLOR = By.xpath("(//div[@class=' css-1wy0on6']/div)[3]");
}
