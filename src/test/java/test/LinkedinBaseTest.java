package test;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import page.LinkedinLoginPage;

/**
 * LinkedinBase test class with common for other tests methods.
 */
public class LinkedinBaseTest extends Browser {
    String testSite="https://www.linkedin.com/";
    protected String userEmail="altestqa@gmail.com";
    protected String userPW="Aqqq2222";

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
        setBrowser();
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
