package conduit.test.chain;

import org.openqa.selenium.By;

public class MainPage {

    By categoryListButton = new By.ByCssSelector("[data-target='nav-categories']");

    By categoryMakeupLink = new By.ByCssSelector("[data-target='cat_78']");

    public MainPage click() {
        return this ;
    }

    public void check() {

    }
}
