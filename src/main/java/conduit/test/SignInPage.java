package conduit.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignInPage extends BasePage{

    WebDriver driver;

    @FindBy(className = "error-messages")
    public By MESSAGE_ERROR;

    @FindBy(css = "[type='email']")
    public By INPUT_EMAIL;

    @FindBy(css = "[type='password']")
    public By INPUT_PASSWORD;

    @FindBy(css = "[type='submit']")
    public By BUTTON_SUBMIT;

    public SignInPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void enterEmail(String email) {
        driver.findElement(INPUT_EMAIL).sendKeys(email);
    }
}
