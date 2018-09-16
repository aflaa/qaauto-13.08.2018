package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import page.LinkedinLoginPage;

/**
 * LinkedinBase test class with common for other tests methods.
 */
public class LinkedinBaseTest {
    String testSite="https://www.linkedin.com/";
    protected String userEmail="altestqa@gmail.com";
    protected String userPW="Aqqq2222";

    WebDriver driver;
    LinkedinLoginPage linkedinLoginPage;

    /**
     * BeforeMethod - method executed before every Test.
     *
     * Scenario:
     * - Open Chrome browser.
     * - Set browser option to maximaze window.
     * - Navigate to test site link.
     * - Create LinkedinLoginPage.
     */
    @BeforeMethod //BeforeTest doesn't work as expected
    public void beforeMethod() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("useAutomationExtension",false);
        chromeOptions.addArguments("start-maximized");
        driver =new ChromeDriver(chromeOptions);
        driver.get(testSite);
        linkedinLoginPage =  new LinkedinLoginPage(driver);
    }

    /**
     * AfterMethod - method executed after every Test.
     *
     * Scenario:
     * -Quit from browser.
     */
    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }

}
