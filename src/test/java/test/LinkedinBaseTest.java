package test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import page.LinkedinLoginPage;

public class LinkedinBaseTest {
    String testSite="https://www.linkedin.com/";
    WebDriver driver;
    LinkedinLoginPage linkedinLoginPage;

    @BeforeMethod //BeforeTest doesn't work as expected
    public void beforeMethod() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("useAutomationExtension",false);
        chromeOptions.addArguments("start-maximized");
        driver =new ChromeDriver(chromeOptions);
        driver.get(testSite);
        linkedinLoginPage =  new LinkedinLoginPage(driver);
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }

}
