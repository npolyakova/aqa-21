package conduit.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class SignUpPage extends BasePage{

    WebDriver driver;

    //INPUT_USERNAME or inputUsername
    public By MESSAGE_ERROR = By.className("error-messages");

    public By INPUT_USERNAME = By.cssSelector("[type='text']");

    public By INPUT_EMAIL = By.cssSelector("[type='email']");

    public By INPUT_PASSWORD = By.cssSelector("[type='password']");

    public By BUTTON_SUBMIT = By.cssSelector("[type='submit']");

    public SignUpPage(WebDriver driver) {
        this.driver = driver;
    }

    public void enterName(String name) {
        driver.findElement(INPUT_USERNAME).sendKeys(name);
    }

    public void enterEmail(String email) {
        driver.findElement(INPUT_EMAIL).sendKeys(email);
    }

    public void enterPassword(String password) {
        driver.findElement(INPUT_PASSWORD).sendKeys(password);
    }
}
