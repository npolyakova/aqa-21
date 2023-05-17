package conduit.test;

import org.openqa.selenium.By;

public class SignInPage extends BasePage{

    public By MESSAGE_ERROR = By.className("error-messages");

    public By INPUT_EMAIL = By.cssSelector("[type='email']");

    public By INPUT_PASSWORD = By.cssSelector("[type='password']");

    public By BUTTON_SUBMIT = By.cssSelector("[type='submit']");
}
