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

       Assert.assertTrue(linkedinLoginPage.isPageLoaded(),"Login page is not loaded");
       LinkedInHomePage linkedInHomePage =  linkedinLoginPage.login(userEmail,userPassword);

       Assert.assertTrue(linkedInHomePage.isPageLoaded(),"Home page is not loaded");
       linkedInHomePage.clickSignOut();
    }

    @DataProvider
    public Object[][] invalidDataProvider() {
        return new Object[][]{
                { "a@b.c", "wrong","Please enter a valid email address.", "The password you provided must have at least 6 characters." },
                { "ba"+email, PW ,"Hmm, we don't recognize that email. Please try again.", "" },
                { email, PW+PW ,"", "Hmm, that's not the right password. Please try again or request a new one." },
                { "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa129@gmail.com", PW ,"The text you provided is too long (the maximum length is 128 characters, your text contains 129 characters).", "" }
        };
    }
    @Test(dataProvider = "invalidDataProvider")
    public void negativeLoginErrorsTest (String userEmail, String userPassword, String userEmailAlertMessage, String userPasswordAlertMessage){
        //navigate to login site
        //fill in with short email and password
        //verify Login and Password hint messages are correct

        LinkedinLoginPage linkedinLoginPage =  new LinkedinLoginPage(driver);
        Assert.assertTrue(linkedinLoginPage.isPageLoaded(),"Login page is not loaded");

        LinkedInErrorPage linkedInErrorPage = linkedinLoginPage.login(userEmail,userPassword);
        Assert.assertTrue(linkedInErrorPage.isPageLoaded(),"ErrorPage is not loaded");

        Assert.assertEquals(linkedInErrorPage.getAlertMessageText(), "There were one or more errors in your submission. Please correct the marked fields below.", "Alert message text is wrong.");
        Assert.assertEquals(linkedInErrorPage.getUserEmailAlertText(), userEmailAlertMessage, "User Email alert message text is wrong.");
        Assert.assertEquals(linkedInErrorPage.getPWAlertText(), userPasswordAlertMessage, "User Password alert message text is wrong.");

    }

    @Test
    public void negativeEmptyLoginTest (){
        //navigate to login site
        //leave empty email and password/or one of them
        //verify that signIn button disabled and logon impossible

        LinkedinLoginPage linkedinLoginPage =  new LinkedinLoginPage(driver);
        Assert.assertTrue(linkedinLoginPage.isPageLoaded(),"Login page is not loaded");

        linkedinLoginPage  =  linkedinLoginPage.login("","");

        Assert.assertTrue(linkedinLoginPage.isSignInDisabled(), "signIn button is not disabled on Error page, when Login and PW empty");
        linkedinLoginPage.login(email,"");
        Assert.assertTrue(linkedinLoginPage.isSignInDisabled(), "signIn button is not disabled on Error page, when PW empty");

        linkedinLoginPage.clearEmail();
        linkedinLoginPage.login("",PW);
        Assert.assertTrue(linkedinLoginPage.isSignInDisabled(), "signIn button is not disabled on Error page, when Login empty");
    }

    @DataProvider
    public Object[][] invalidBackDataProvider() {
        return new Object[][]{
                { "ALtestQA@gmail.com", "21122112" }
        };
    }

    @Test(dataProvider = "invalidBackDataProvider")
    public void negativeLogoutBackLoginTest (String userEmail, String userPassword) {
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
       LinkedInHomePage linkedInHomePage =  linkedinLoginPage.login(userEmail,userPassword);
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
        Assert.assertTrue(linkedinLoginPage.isPageLoaded(),"Login page is not loaded");
    }

}
