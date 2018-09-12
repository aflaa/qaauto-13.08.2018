package page;

import org.openqa.selenium.WebDriver;
import util.GMailService;

public class LinkedinBasePage {
    protected WebDriver driver;
    protected static GMailService gMailService = new GMailService();
    protected static String receivedEmail;

    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }

    public String getCurrentTitle() {
        return driver.getTitle();
    }
}
