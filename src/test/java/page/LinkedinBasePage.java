package page;

import org.openqa.selenium.WebDriver;

public class LinkedinBasePage {
    protected WebDriver driver;

    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }

    public String getCurrentTitle() {
        return driver.getTitle();
    }
}
