import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class LinkedinLoginTest {

    String testSite="https://www.linkedin.com/";
    String email="altestqa@gmail.com";
    String PW="21122112";
    String userName="al lena";
    String signInButton_xpath="//input[@class='login submit-button']";
    String loginField_xpath="//input[@class='login-email']";
    String pwField_xpath="//input[@class='login-password']";
    String profilePhoto_xpath="//img[@class='nav-item__profile-member-photo nav-item__icon ghost-person']";
    String signOutButton_xpath="//a[@data-control-name='nav.settings_signout']";

    WebDriver driver;

    @BeforeMethod //BeforeTest doesn't work as expected
    public void beforeMethod() {
      ChromeOptions chromeOptions = new ChromeOptions();
      chromeOptions.setExperimentalOption("useAutomationExtension",false);
      chromeOptions.addArguments("start-maximized");
      driver =new ChromeDriver(chromeOptions);
      driver.get(testSite);
    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }

    @Test
    public void succeedfulLoginTest () {
        //navigate to linkedin.com
        //verify that login page is loaded
        //enter userEmail
        //enter userPW
        //click on 'Sign in' button
        //verify Home page is displayed.

       LinkedinLoginPage linkedinLoginPage =  new LinkedinLoginPage(driver);
       Assert.assertTrue(linkedinLoginPage.isPageLoaded(),"Login page is not loaded");

       linkedinLoginPage.login(email,PW);

        LinkedInHomePage linkedInHomePage =  new LinkedInHomePage(driver);
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

        Assert.assertTrue(linkedInErrorPage.isLongErrorMessagesMatch(), "Error message for a long login doesn't match");
    }

    @Test
    public void negativeEmptyLoginTest (){
        //navigate to login site
        //leave empty email and password/or one of them
        //verify that signIn button disabled and logon impossible

//        Assert.assertEquals(driver.getCurrentUrl(), testSite,"Login page URL is wrong.");
//        Assert.assertEquals(driver.getTitle(), "LinkedIn: Log In or Sign Up","Login page title is wrong.");
//
//        WebElement userEmailField=driver.findElement(By.xpath(loginField_xpath));
//        WebElement userPasswordField=driver.findElement(By.xpath(pwField_xpath));
//        WebElement signInButton=driver.findElement(By.xpath(signInButton_xpath));
//
//        Assert.assertTrue(signInButton.isDisplayed(), "signIn button is not displayed on Login page");

//        userEmailField.sendKeys("");
//        userPasswordField.sendKeys("");
//        signInButton.click();

        LinkedinLoginPage linkedinLoginPage =  new LinkedinLoginPage(driver);
        Assert.assertTrue(linkedinLoginPage.isPageLoaded(),"Login page is not loaded");
        linkedinLoginPage.login("","");

       //in disabled Assert.assertTrue(linkedInErrorPage.isPageLoaded(),"Login page after error is not loaded");

        Assert.assertTrue(linkedinLoginPage.isSignInDisabled(), "signIn button is not disabled on Error page, when Login and PW empty");

 //       Assert.assertFalse(signInButton.isEnabled(),"signIn button is not disabled, when Login and PW empty.");

        //LinkedInErrorPage linkedInErrorPage =  new LinkedInErrorPage(driver);
        linkedinLoginPage.login(email,"");

        //Assert.assertFalse(signInButton.isEnabled(),"signIn button is not disabled, when PW empty.");
        Assert.assertTrue(linkedinLoginPage.isSignInDisabled(), "signIn button is not disabled on Error page, when PW empty");

 //       userEmailField.clear();
        linkedinLoginPage.clearEmail();
        linkedinLoginPage.login("",PW);
        //Assert.assertFalse(signInButton.isEnabled(),"signIn button is not disabled, when PW empty.");
        Assert.assertTrue(linkedinLoginPage.isSignInDisabled(), "signIn button is not disabled on Error page, when Login empty");

//        userEmailField.sendKeys("");
//        userPasswordField.sendKeys(PW);
//        signInButton.click();
//        Assert.assertFalse(signInButton.isEnabled(),"signIn button is not disabled, when Login empty.");
    }

    @Test
    public void negativeLoginWrongValuesTest (){
        //navigate to login site
        //fill in email and password wrong/incorrect values
        //verify for Login and Password hint messages are correct for incorrect values

        LinkedinLoginPage linkedinLoginPage =  new LinkedinLoginPage(driver);
        Assert.assertTrue(linkedinLoginPage.isPageLoaded(),"Login page is not loaded");
        linkedinLoginPage.login("a"+email,PW);

        LinkedInErrorPage linkedInErrorPage =  new LinkedInErrorPage(driver);
        Assert.assertTrue(linkedInErrorPage.isPageLoaded(),"Login page after error is not loaded");
        Assert.assertTrue(linkedInErrorPage.isWrongLoginErrorMessageMatch(), "Login hint message does not match.");

        linkedInErrorPage.clearEmail();
        linkedInErrorPage.login(email,PW+PW);
        Assert.assertTrue(linkedInErrorPage.isWrongPwErrorMessageMatch(), "Password hint message does not match.");

        //       Assert.assertTrue(linkedInErrorPage.isSignInDisabled(), "signIn button is not disabled on Error page, when Login empty");


//        Assert.assertEquals(driver.getCurrentUrl(), testSite,"Login page URL is wrong.");
//        Assert.assertEquals(driver.getTitle(), "LinkedIn: Log In or Sign Up","Login page title is wrong.");
//
//        WebElement userEmailField=driver.findElement(By.xpath(loginField_xpath));
//        WebElement userPasswordField=driver.findElement(By.xpath(pwField_xpath));
//        WebElement signInButton=driver.findElement(By.xpath(signInButton_xpath));

//        Assert.assertTrue(signInButton.isDisplayed(), "signIn button is not displayed on Login page.");
//        userEmailField.sendKeys("a"+ email);
//        userPasswordField.sendKeys(PW);
//        signInButton.click();

        //  Assert.assertEquals(driver.getCurrentUrl(), "https://www.linkedin.com/uas/login-submit","Login page URL is wrong.");
        //  Assert.assertEquals(driver.getTitle(), "Sign In to LinkedIn","Login page title is wrong.");

//        try {
//            sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        WebElement alertMessage = driver.findElement(By.xpath("//div[@role='alert']"));
//        Assert.assertEquals(alertMessage.getText(),
//                "There were one or more errors in your submission. Please correct the marked fields below.",
//                "Alert message test is wrong.");
//
//        WebElement loginHintMessage =driver.findElement(By.xpath("//span[@id='session_key-login-error']"));
//        Assert.assertEquals(loginHintMessage.getText(),
//                "Hmm, we don't recognize that email. Please try again.",
//                "Login hint message does not match.");
//
//        WebElement loginErrorField = driver.findElement(By.xpath("//input[@id='session_key-login']"));
//        WebElement pwErrorField = driver.findElement(By.xpath("//input[@id='session_password-login']"));
//
//        loginErrorField.clear();
//        loginErrorField.sendKeys(email);
//        pwErrorField.sendKeys(PW+PW);
//        driver.findElement(By.xpath("//input[@name='signin']")).click();
//
//        try {
//            sleep(3000);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        WebElement passwordHintMessage =driver.findElement(By.xpath("//span[@id='session_password-login-error']"));
//        Assert.assertEquals(passwordHintMessage.getText(),
//                "Hmm, that's not the right password. Please try again or request a new one.",
//                "Password hint message does not match.");

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
