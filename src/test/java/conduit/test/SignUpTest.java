package conduit.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

public class SignUpTest extends BaseTest {

    private SignUpPage signUpPage = new SignUpPage();
    private BasePage basePage = new BasePage();

    @BeforeEach
    public void setUrl() {
        baseUrl = "https://react-redux.realworld.io/#";
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofMillis(8000));
    }

    @Test
    public void shouldSignUpWithCorrectData() {
        String name = faker.name().username();
        String email = faker.internet().emailAddress();
        String pass = faker.internet().password();

        driver.get(baseUrl + "/register");

        driver.findElement(signUpPage.INPUT_USERNAME).sendKeys(name);
        driver.findElement(signUpPage.INPUT_EMAIL).sendKeys(email);
        driver.findElement(signUpPage.INPUT_PASSWORD).sendKeys(pass);
        driver.findElement(signUpPage.BUTTON_SUBMIT).click();

        assertThat(driver.findElement(signUpPage.BUTTON_SUBMIT).isEnabled()).isFalse();
        wait.until(driver -> driver.findElement(By.xpath("//a[text()='Your Feed']")));
        driver.findElement(By.cssSelector(".nav-link[href='#@" + name + "']")).isDisplayed();
    }

    @Test
    public void shouldShowValidationErrorIfEmailIsIncorrect() {
        String name = faker.name().username();
        String email = faker.name().username();
        String pass = faker.internet().password();

        driver.get(baseUrl + "/register");

        driver.findElement(signUpPage.INPUT_USERNAME).sendKeys(name);
        driver.findElement(signUpPage.INPUT_EMAIL).sendKeys(email);
        driver.findElement(signUpPage.INPUT_PASSWORD).sendKeys(pass);
        driver.findElement(signUpPage.BUTTON_SUBMIT).click();

        assertThat(driver.findElement(By.cssSelector("input:invalid")).isDisplayed()).isTrue();
    }

    @Test
    public void shouldNotSignUpWithBlankEmail() {
        driver.get(baseUrl + "/register");

        driver.findElement(signUpPage.BUTTON_SUBMIT).click();

        basePage.checkErrorMessage(
                signUpPage.MESSAGE_BLANK_VALUE,
                wait,
                driver,
                "email",
                "can't be blank"
        );
    }

    @Test
    public void shouldNotSignUpWithBlankUsername() {
        String email = faker.internet().emailAddress();

        driver.get(baseUrl + "/register");

        driver.findElement(signUpPage.INPUT_EMAIL).sendKeys(email);
        driver.findElement(signUpPage.BUTTON_SUBMIT).click();

        basePage.checkErrorMessage(
                signUpPage.MESSAGE_BLANK_VALUE,
                wait,
                driver,
                "username",
                "can't be blank"
        );
    }

    @Test
    public void shouldNotSignUpWithBlankPassword() {
        String email = faker.internet().emailAddress();
        String username = faker.name().username();

        driver.get(baseUrl + "/register");

        driver.findElement(signUpPage.INPUT_USERNAME).sendKeys(username);
        driver.findElement(signUpPage.INPUT_EMAIL).sendKeys(email);
        driver.findElement(signUpPage.BUTTON_SUBMIT).click();

        basePage.checkErrorMessage(
                signUpPage.MESSAGE_BLANK_VALUE,
                wait,
                driver,
                "password",
                "can't be blank"
        );
    }
}
