package conduit.test.steps;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.assertj.core.api.Assertions.assertThat;

public class SignInUpSteps {
    public By INPUT_EMAIL = By.cssSelector("[type='email']");

    public By INPUT_PASSWORD = By.cssSelector("[type='password']");

    public By BUTTON_SUBMIT = By.cssSelector("[type='submit']");
    WebDriver driver;

    public void login() {
        driver.findElement(INPUT_EMAIL).sendKeys("noemail@here.com");
        driver.findElement(INPUT_PASSWORD).sendKeys("11111111");
        driver.findElement(BUTTON_SUBMIT).click();

        assertThat(driver.findElement(BUTTON_SUBMIT).isEnabled()).isFalse();

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofMillis(8000));
        wait.until(driver -> driver.findElement(By.xpath("//a[text()='Your Feed']")));
        driver.findElement(By.cssSelector(".nav-link[href='#@Natalya']")).isDisplayed();
    }

    public void enterEmailAndPassword(String email, String password) {
        driver.findElement(INPUT_PASSWORD).sendKeys(email);
        driver.findElement(INPUT_PASSWORD).sendKeys(password);
        driver.findElement(BUTTON_SUBMIT).click();
    }

    public SignInUpSteps(WebDriver driver) {
        this.driver = driver;
    }
}
