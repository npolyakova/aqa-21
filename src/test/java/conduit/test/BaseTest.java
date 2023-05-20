package conduit.test;

import com.github.javafaker.Faker;
import conduit.test.value.User;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BaseTest {
    public static ChromeDriver driver;

    public String baseUrl;

    public WebDriverWait wait;

    public static final Faker faker = new Faker();

    @BeforeAll
    public static void setProperty() {
        System.setProperty("webdriver.http.factory", "jdk-http-client");
    }

    @AfterEach
    public void closeAfterEach() {
        driver.close();
    }

    public User fakeUser() {
        return User.newBuilder()
                .setUserName(faker.name().username())
                .setUserEmail(faker.internet().emailAddress())
                .setUserPassword(faker.internet().password())
                .build();
    }
}
