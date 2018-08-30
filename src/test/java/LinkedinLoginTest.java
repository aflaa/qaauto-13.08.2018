import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class LinkedinLoginTest {

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

    @BeforeMethod //BeforeTest doesn't work as expected
    public void beforeMethod() {
      ChromeOptions chromeOptions = new ChromeOptions();
      chromeOptions.setExperimentalOption("useAutomationExtension",false);
      chromeOptions.addArguments("start-maximized");
      driver =new ChromeDriver(chromeOptions);
      driver.get(testSite);
     //need to update LinkedinLoginPage linkedinLoginPage =  new LinkedinLoginPage(driver);
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }

    @DataProvider
    public Object[][] validDataProvider() {
        return new Object[][]{
                { "altestqa@gmail.com", "21122112"},
                { "ALtestQA@gmail.com", "21122112" }
        };
    }

    @Test(dataProvider = "validDataProvider")
    public void succeedfulLoginTest (String userEmail, String userPassword) {
        //navigate to linkedin.com
        //verify that login page is loaded
        //enter userEmail
        //enter userPW
        //click on 'Sign in' button
        //verify Home page is displayed.

       LinkedinLoginPage linkedinLoginPage =  new LinkedinLoginPage(driver);
       Assert.assertTrue(linkedinLoginPage.isPageLoaded(),"Login page is not loaded");
       LinkedInHomePage linkedInHomePage =  linkedinLoginPage.login(userEmail,userPassword);
       Assert.assertTrue(linkedInHomePage.isPageLoaded(),"Home page is not loaded");
       linkedInHomePage.clickSignOut();
    }

    @Test
    public void negativeLoginShortValuesTest (){
        //navigate to login site
        //fill in with short email and password
        //verify Login and Password hint messages are correct

        email="a@b.c";
        PW="wrong";

        LinkedinLoginPage linkedinLoginPage =  new LinkedinLoginPage(driver);
        Assert.assertTrue(linkedinLoginPage.isPageLoaded(),"Login page is not loaded");
        linkedinLoginPage.login(email,PW);

        LinkedInErrorPage linkedInErrorPage =  new LinkedInErrorPage(driver);
        Assert.assertTrue(linkedInErrorPage.isPageLoaded(),"Login page after error is not loaded");

        Assert.assertTrue(linkedInErrorPage.isShortErrorMessagesMatch(), "Error messages don't match");
    }

    @Test
    public void negativeLoginLongValuesTest (){
        //navigate to login site
        //fill in email field with long email =129 characters
        //verify Login and password hint messages are correct

        email="aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa129@gmail.com";

        LinkedinLoginPage linkedinLoginPage =  new LinkedinLoginPage(driver);
        Assert.assertTrue(linkedinLoginPage.isPageLoaded(),"Login page is not loaded");
        linkedinLoginPage.login(email,PW);

        LinkedInErrorPage linkedInErrorPage =  new LinkedInErrorPage(driver);
      //  Assert.assertTrue(linkedInErrorPage.isPageLoaded(),"Login page after error is not loaded");
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(linkedInErrorPage.isLongErrorMessagesMatch(), "Error message for a long login doesn't match");
    }

    @Test
    public void negativeEmptyLoginTest (){
        //navigate to login site
        //leave empty email and password/or one of them
        //verify that signIn button disabled and logon impossible

        LinkedinLoginPage linkedinLoginPage =  new LinkedinLoginPage(driver);
        Assert.assertTrue(linkedinLoginPage.isPageLoaded(),"Login page is not loaded");
       // linkedinLoginPage.login("","");

        linkedinLoginPage  =  linkedinLoginPage.login("","");
        //new LinkedInHomePage(driver);

        Assert.assertTrue(linkedinLoginPage.isSignInDisabled(), "signIn button is not disabled on Error page, when Login and PW empty");
        linkedinLoginPage.login(email,"");
        Assert.assertTrue(linkedinLoginPage.isSignInDisabled(), "signIn button is not disabled on Error page, when PW empty");

        linkedinLoginPage.clearEmail();
        linkedinLoginPage.login("",PW);
        Assert.assertTrue(linkedinLoginPage.isSignInDisabled(), "signIn button is not disabled on Error page, when Login empty");
    }

    @Test
    public void negativeLoginWrongValuesTest (){
        //navigate to login site
        //fill in email and password wrong/incorrect values
        //verify for Login and Password hint messages are correct for incorrect values

        LinkedinLoginPage linkedinLoginPage =  new LinkedinLoginPage(driver);
        Assert.assertTrue(linkedinLoginPage.isPageLoaded(),"Login page is not loaded");
        linkedinLoginPage.login("ba"+email,PW);

        LinkedInErrorPage linkedInErrorPage =  new LinkedInErrorPage(driver);
   //     Assert.assertTrue(linkedInErrorPage.isPageLoaded(),"Login page after error is not loaded");
        Assert.assertTrue(linkedInErrorPage.isWrongLoginErrorMessageMatch(), "Login hint message does not match.");
        linkedInErrorPage.clearEmail();

        linkedInErrorPage.login(email,PW+PW);
        Assert.assertTrue(linkedInErrorPage.isWrongPwErrorMessageMatch(), "Password hint message does not match.");
    }

    @Test
    public void negativeLogoutBackLoginTest () {
        //navigate to linkedin.com
        //verify that login page is loaded
        //enter userEmail
        //enter userPW
        //click on 'Sign in' button
        //click on 'Logout' button
        //click Back
        //verify that user is not logged in

        LinkedinLoginPage linkedinLoginPage =  new LinkedinLoginPage(driver);
        Assert.assertTrue(linkedinLoginPage.isPageLoaded(),"Login page is not loaded");

        linkedinLoginPage.login(email,PW);

        LinkedInHomePage linkedInHomePage =  new LinkedInHomePage(driver);
        Assert.assertTrue(linkedInHomePage.isPageLoaded(),"Home page is not loaded");
        linkedInHomePage.clickSignOut();

       // driver.findElement(By.xpath(signOutButton_xpath)).click();

        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.navigate().back();

        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(driver.getTitle(), "LinkedIn: Log In or Sign Up","Login page title is wrong.");
      //  driver.navigate().refresh();
       // Assert.assertNull(profileNavItem, "Profile menu is missing");
        //Assert.assertTrue(signInButton.isEnabled(), "Sign in button again displayed");
    }

}
