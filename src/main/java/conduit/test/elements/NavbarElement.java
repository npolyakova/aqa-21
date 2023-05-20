package conduit.test.elements;

import org.openqa.selenium.By;

public class NavbarElement {

    By logo = new By.ByClassName("navbar-brand");

    By home = new By.ByCssSelector("[href=\"#/\"]");

    By login = new By.ByCssSelector("[href=\"#login\"]");

    By reg = new By.ByCssSelector("[href=\"#register\"]");

    // After login
    By post = new By.ByCssSelector("[href=\"#editor\"]");

    By settings = new By.ByCssSelector("[href=\"#settings\"]");

    By username = new By.ByCssSelector("[href=\"#@Natalya\"]");
}
