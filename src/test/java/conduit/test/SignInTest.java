package conduit.test;

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

public class SignInTest extends BaseTest {

    private SignInPage signInPage = new SignInPage();
    private SignUpPage signUpPage = new SignUpPage();
    private BasePage basePage = new BasePage();

    @BeforeEach
    public void setUrl() {
        baseUrl = "https://react-redux.realworld.io/#/login";
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, Duration.ofMillis(8000));
    }

    @Test
    public void shouldLoginWithCorrectData() {
        driver.get(baseUrl);

        driver.findElement(signInPage.INPUT_EMAIL).sendKeys("noemail@here.com");
        driver.findElement(signInPage.INPUT_PASSWORD).sendKeys("11111111");
        driver.findElement(signInPage.BUTTON_SUBMIT).click();

        assertThat(driver.findElement(signInPage.BUTTON_SUBMIT).isEnabled()).isFalse();

        //TODO
        wait.until(driver -> driver.findElement(By.xpath("//a[text()='Your Feed']")));
        driver.findElement(By.cssSelector(".nav-link[href='#@Natalya']")).isDisplayed();
    }

    @ParameterizedTest
    @MethodSource("userData")
    public void shouldNotSignInWithIncorrectLogin(String email, String password) {
        driver.get(baseUrl);

        driver.findElement(signInPage.INPUT_EMAIL)
                .sendKeys(email);
        driver.findElement(signInPage.INPUT_PASSWORD).sendKeys(password);
        driver.findElement(signInPage.BUTTON_SUBMIT).click();

        basePage.checkErrorMessage(
                signUpPage.MESSAGE_BLANK_VALUE,
                wait,
                driver,
                "email or password",
                "is invalid"
        );
    }

    @Test
    public void shouldNotLoginWithoutData() {

    }

    @Test
    public void shouldNotLoginWithoutLogin() {

    }

    @Test
    public void shouldNotLoginWithoutPassword() {

    }

    @Test
    public void shouldShowValidationErrorIfEmailIsInvalid() {

    }

    static Stream<Arguments> userData() {
        return Stream.of(
                Arguments.of(faker.internet().emailAddress() + new Date().getTime(), "11111111"),
                Arguments.of("noemail@here.com" + new Date().getTime(), faker.internet().password())
        );
    }
}
