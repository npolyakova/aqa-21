package conduit.test;

import conduit.test.steps.SignInUpSteps;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.Date;
import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

public class SignInStepsTest extends BaseTest {

    public By INPUT_EMAIL = By.cssSelector("[type='email']");

    public By INPUT_PASSWORD = By.cssSelector("[type='password']");

    public By BUTTON_SUBMIT = By.cssSelector("[type='submit']");

    public By MESSAGE_ERROR = By.className("error-messages");

    SignInUpSteps signInSteps;

    @BeforeEach
    public void setUrl() {
        baseUrl = "https://react-redux.realworld.io/#/login";
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofMillis(8000));
        signInSteps = new SignInUpSteps(driver);
        driver.get(baseUrl);
    }

    @Test
    public void shouldLoginWithCorrectData() {
        signInSteps.login();
    }

    @ParameterizedTest
    @MethodSource("userData")
    public void shouldNotSignInWithIncorrectLogin(String email, String password) {
        signInSteps.enterEmailAndPassword(email, password);

        // TODO can be a step
        wait.until(driver -> driver.findElement(MESSAGE_ERROR));
        assertThat(driver.findElement(MESSAGE_ERROR).getText())
                .contains("email or password", "is invalid");
    }

    @Test
    public void shouldNotLoginWithoutData() {
        // TODO can be a step
        driver.findElement(BUTTON_SUBMIT).click();

        // TODO can be a step
        wait.until(driver -> driver.findElement(MESSAGE_ERROR));
        assertThat(driver.findElement(MESSAGE_ERROR).getText())
                .contains("email", "can't be blank");
    }

    @Test
    public void shouldNotLoginWithoutLogin() {
        String password = faker.internet().password();

        driver.findElement(INPUT_PASSWORD).sendKeys(password);
        driver.findElement(BUTTON_SUBMIT).click();

        wait.until(driver -> driver.findElement(MESSAGE_ERROR));
        assertThat(driver.findElement(MESSAGE_ERROR).getText())
                .contains("email", "can't be blank");
    }

    @Test
    public void shouldNotLoginWithoutPassword() {
        String email = faker.internet().emailAddress();

        driver.findElement(INPUT_EMAIL).sendKeys(email);
        driver.findElement(BUTTON_SUBMIT).click();

        wait.until(driver -> driver.findElement(MESSAGE_ERROR));
        assertThat(driver.findElement(MESSAGE_ERROR).getText())
                .contains("password", "can't be blank");
    }

    @Test
    public void shouldShowValidationErrorIfEmailIsInvalid() {
        String email = faker.name().username();
        String pass = faker.internet().password();

        driver.findElement(INPUT_EMAIL).sendKeys(email);
        driver.findElement(INPUT_PASSWORD).sendKeys(pass);
        driver.findElement(BUTTON_SUBMIT).click();

        assertThat(driver.findElement(By.cssSelector("input:invalid")).isDisplayed()).isTrue();
    }

    static Stream<Arguments> userData() {
        return Stream.of(
                Arguments.of(faker.internet().emailAddress() + new Date().getTime(), "11111111"),
                Arguments.of("noemail@here.com" + new Date().getTime(), faker.internet().password())
        );
    }
}
