package __common;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.extension.ExtendWith;
import ui.demoqa.selenium.common.AllureReportExtension;

@ExtendWith(AllureReportExtension.class)
public abstract class BaseCommonTest {
    @BeforeAll
    static void setUpAll() {
        AllureEnvironment.write();
    }
}