import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class LinkedinResetPasswordTest {
    String testSite="https://www.linkedin.com/";
    String email="altestqa@gmail.com";
    String PW="21122112";
//    String userName="al lena";
//    String signInButton_xpath="//input[@class='login submit-button']";
//    String loginField_xpath="//input[@class='login-email']";
//    String pwField_xpath="//input[@class='login-password']";
//    String profilePhoto_xpath="//img[@class='nav-item__profile-member-photo nav-item__icon ghost-person']";
//    String signOutButton_xpath="//a[@data-control-name='nav.settings_signout']";

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

    @Test
    public void resetPasswordTest () {
        //navigate to linkedin.com
        //verify that login page is loaded
        //click reset  PW
        //verify that Forgot PW page is loaded
        //fill in fields to get link
        //verify that secure link page is loaded
        //sleep
        //click link
        //verify that new password page is loaded
        //verify that home page is loaded

        Assert.assertTrue(linkedinLoginPage.isPageLoaded(),"Login page is not loaded");
        LinkedinForgotPasswordPage linkedinForgotPasswordPage = linkedinLoginPage.forgotPasswordClick();

        Assert.assertTrue(linkedinForgotPasswordPage.isPageLoaded(),"Forgotpassword page is not loaded");

        LinkedinSecureLinkPage linkedinSecureLinkPage =linkedinForgotPasswordPage.findAccount();

        Assert.assertTrue(linkedinSecureLinkPage.isPageLoaded(),"Secure link page is not loaded");

        /////////////////////////////////////////////manual link click//////////////////////////
        try {
            sleep(24000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        LinkedinNewPasswordPage linkedinNewPasswordPage =new LinkedinNewPasswordPage(driver);

        LinkedinPasswordChangedPage linkedinPasswordChangedPage = linkedinNewPasswordPage.newPasswordSend();
        LinkedInHomePage linkedInHomePage = linkedinPasswordChangedPage.goHome();

        Assert.assertTrue(linkedInHomePage.isPageLoaded(),"Home page is not loaded");

    }

}
