package conduit.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.assertj.core.api.Assertions.assertThat;

public class BasePage {

    public void checkErrorMessage(
            By selector,
            WebDriverWait wait,
            WebDriver webDriver,
            String field,
            String errorMessage
    ) {
        wait.until(driver -> driver.findElement(selector));
        assertThat(webDriver.findElement(selector).getText())
                .contains(field, errorMessage);
    }
}
