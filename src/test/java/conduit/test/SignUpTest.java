package conduit.test;

import conduit.test.value.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

public class SignUpTest extends BaseTest {

    private SignUpPage signUpPage;
    private BasePage basePage = new BasePage();

    @BeforeEach
    public void setUrl() {
        baseUrl = "https://react-redux.realworld.io/#";
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofMillis(8000));
        signUpPage = new SignUpPage(driver);
    }

    @Test
    public void shouldSignUpWithCorrectData() {
        final User user = fakeUser();

        driver.get(baseUrl + "/register");

        signUpPage.enterName(user.getUsername());
        signUpPage.enterEmail(user.getEmail());
        signUpPage.enterPassword(user.getPassword());
        driver.findElement(signUpPage.BUTTON_SUBMIT).click();

        assertThat(driver.findElement(signUpPage.BUTTON_SUBMIT).isEnabled()).isFalse();
        wait.until(driver -> driver.findElement(By.xpath("//a[text()='Your Feed']")));
        driver.findElement(By.cssSelector(".nav-link[href='#@" + user.getUsername() + "']")).isDisplayed();
    }

    @Test
    public void shouldShowValidationErrorIfEmailIsIncorrect() {
        final User user = fakeUser();

        driver.get(baseUrl + "/register");

        driver.findElement(signUpPage.INPUT_USERNAME).sendKeys(user.getUsername());
        driver.findElement(signUpPage.INPUT_EMAIL).sendKeys(user.getEmail());
        driver.findElement(signUpPage.INPUT_PASSWORD).sendKeys(user.getPassword());
        driver.findElement(signUpPage.BUTTON_SUBMIT).click();

        assertThat(driver.findElement(By.cssSelector("input:invalid")).isDisplayed()).isTrue();
    }

    @Test
    public void shouldNotSignUpWithBlankEmail() {
        driver.get(baseUrl + "/register");

        driver.findElement(signUpPage.BUTTON_SUBMIT).click();

        basePage.checkErrorMessage(
                signUpPage.MESSAGE_ERROR,
                wait,
                driver,
                "email",
                "can't be blank"
        );
    }

    @Test
    public void shouldNotSignUpWithBlankUsername() {
        User newUser = User.newBuilder().setUserEmail(faker.internet().emailAddress()).build();

        driver.get(baseUrl + "/register");

        driver.findElement(signUpPage.INPUT_EMAIL).sendKeys(newUser.getEmail());
        driver.findElement(signUpPage.BUTTON_SUBMIT).click();

        basePage.checkErrorMessage(
                signUpPage.MESSAGE_ERROR,
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
                signUpPage.MESSAGE_ERROR,
                wait,
                driver,
                "password",
                "can't be blank"
        );
    }
}
