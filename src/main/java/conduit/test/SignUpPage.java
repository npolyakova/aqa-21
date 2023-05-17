package conduit.test;

import org.openqa.selenium.By;

public class SignUpPage extends BasePage{

    //INPUT_USERNAME or inputUsername
    public By MESSAGE_BLANK_VALUE = By.className("error-messages");

    public By INPUT_USERNAME = By.cssSelector("[type='text']");

    public By INPUT_EMAIL = By.cssSelector("[type='email']");

    public By INPUT_PASSWORD = By.cssSelector("[type='password']");

    public By BUTTON_SUBMIT = By.cssSelector("[type='submit']");
}
