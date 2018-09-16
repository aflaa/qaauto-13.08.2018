package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.GMailService;

/**
 * LinkedinBase Object Page with common for other pages methods.
 */
public class LinkedinBasePage {
    protected WebDriver driver;
    protected static GMailService gMailService = new GMailService();
    protected static String receivedEmail;

    /**
     * getCurrent Url method.
     * @return curent URL of page.
     */
    protected String getCurrentUrl(){
        return driver.getCurrentUrl();
    }

    /**
     * getCurrent Title method.
     * @return current title of page.
     */
    protected String getCurrentTitle() {
        return driver.getTitle();
    }

    /**
     * waitUntilElementVisible method.
     *
     * Method waiths untill element become visiable on page,
     * @param webElement -webelement is waiting for.
     * @param timeOutInSec -max of secunds willl wait.
     * @return webElement that was waied for.
     */
    protected WebElement waitUntilElementVisible(WebElement webElement, int timeOutInSec) {
        WebDriverWait wait = new WebDriverWait(driver, timeOutInSec);
        return wait.until(ExpectedConditions.visibilityOf(webElement));
    }
}
