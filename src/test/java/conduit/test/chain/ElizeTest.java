package conduit.test.chain;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class ElizeTest {

    WebDriver driver = new ChromeDriver();

    MainPage mainPage = new MainPage();

    @Test
    public void test() {
        driver.get("...");
        mainPage.click().check();
    }
}
