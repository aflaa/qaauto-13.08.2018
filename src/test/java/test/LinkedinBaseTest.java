package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
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

    String browserName="";
    WebDriver driver;
    LinkedinLoginPage linkedinLoginPage;

    //swich case  when browserName="firefox" then
    // WebDriverManager.firefoxdriver().setup();
    //        driver =new FirefoxDriver(); ..

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
        WebDriverManager.firefoxdriver().setup();
        driver =new FirefoxDriver();
 //       ChromeOptions chromeOptions = new ChromeOptions();
//        chromeOptions.setExperimentalOption("useAutomationExtension",false);
//        chromeOptions.addArguments("start-maximized");
//        driver =new ChromeDriver(chromeOptions);
        driver.get(testSite);
        linkedinLoginPage =  new LinkedinLoginPage(driver);
    }

    /**
     * AfterMethod - method executed after every Test.
     *
     * Scenario:
     * -Quit from browser.
     */
    @AfterMethod(alwaysRun = true)
    public void afterMethod() {
        driver.quit();
    }

}
