package test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.LinkedInErrorPage;
import page.LinkedInHomePage;
import page.LinkedinLoginPage;

/**
 * LinkedinLogin Test class.
 */
public class LinkedinLoginTest extends LinkedinBaseTest{

    String email=userEmail;
    String PW=userPW;

    /**
     * DataProvider with valid Email/Password
     */
    @DataProvider
    public Object[][] validDataProvider() {
        return new Object[][]{
                { email, PW},
                { email.toUpperCase(), PW }
        };
    }

    /**
     * Verify successful user Login.
     *
     * Preconditions:
     * - Open new browser.
     * - Navigate to linkedin.com
     *
     * Scenario:
     * - Verify that login page is loaded.
     * - Enter userEmail.
     * - Enter userPassword.
     * - Click on 'Sign in' button.
     * - Verify Home page is loaded.
     */
    @Test(dataProvider = "validDataProvider")
    public void succeedfulLoginTest (String userEmail, String userPassword) {

       Assert.assertTrue(linkedinLoginPage.isPageLoaded(),"Login page is not loaded");
       LinkedInHomePage linkedInHomePage =  linkedinLoginPage.login(userEmail,userPassword);

       Assert.assertTrue(linkedInHomePage.isPageLoaded(),"Home page is not loaded");
       linkedInHomePage.clickSignOut();
    }

    /**
     * DataProvider with invalid Email/Password
     */
    @DataProvider
    public Object[][] invalidDataProvider() {
        return new Object[][]{
//                { "a@b.c", "wrong","Please enter a valid email address.", "The password you provided must have at least 6 characters." },
//                { "ba"+email, PW ,"Hmm, we don't recognize that email. Please try again.", "" },
//                { email, PW+PW ,"", "Hmm, that's not the right password. Please try again or request a new one." },
                { "aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa129@gmail.com", PW ,"The text you provided is too long (the maximum length is 128 characters, your text contains 129 characters).", "" }
        };
    }

    /**
     * Verify user Login with wrong email and/or password.
     *
     *  Preconditions:
     *  Open new browser.
     *  Navigate to linkedin.com
     *
     *  Scenario:
     *  - Verify that login page is loaded.
     *  - Enter userEmail.
     *  - Enter userPassword.
     * @param userEmail
     * @param userPassword
     * @param userEmailAlertMessage - expected message for Login field
     * @param userPasswordAlertMessage - expected message for Password field
     */
    @Test(dataProvider = "invalidDataProvider")
    public void negativeLoginErrorsTest (String userEmail, String userPassword, String userEmailAlertMessage, String userPasswordAlertMessage){

        LinkedinLoginPage linkedinLoginPage =  new LinkedinLoginPage(driver);
        Assert.assertTrue(linkedinLoginPage.isPageLoaded(),"Login page is not loaded");

        LinkedInErrorPage linkedInErrorPage = linkedinLoginPage.login(userEmail,userPassword);
       // Assert.assertTrue(linkedInErrorPage.isPageLoaded(),"ErrorPage is not loaded");

        Assert.assertEquals(linkedInErrorPage.getAlertMessageText(), "There were one or more errors in your submission. Please correct the marked fields below.", "Alert message text is wrong.");
        Assert.assertEquals(linkedInErrorPage.getUserEmailAlertText(), userEmailAlertMessage, "User Email alert message text is wrong.");
        Assert.assertEquals(linkedInErrorPage.getPWAlertText(), userPasswordAlertMessage, "User Password alert message text is wrong.");

    }
    /**
     * DataProvider with empty Email/Password
     */
    @DataProvider
    public Object[][] emptyDataProvider() {
        return new Object[][]{
                { "", "" },
                { email, "" },
                { "", PW }
        };
    }
    /**
     * Verify user Login with empty email and/or password.
     *
     *  Preconditions:
     *  Open new browser.
     *  Navigate to linkedin.com
     *
     * Scenario:
     * - Verify that login page is loaded.
     * - Enter empty userEmail/userPassword.
     * - Verify that signIn button disabled and logon impossible
     *  @param userEmail
     *  @param userPW
     */
    @Test(dataProvider = "emptyDataProvider" )
    public void negativeEmptyLoginTest (String userEmail, String userPW){

        LinkedinLoginPage linkedinLoginPage =  new LinkedinLoginPage(driver);
        Assert.assertTrue(linkedinLoginPage.isPageLoaded(),"Login page is not loaded");

        linkedinLoginPage.clearEmail();
        linkedinLoginPage  =  linkedinLoginPage.login(userEmail,userPW);

        Assert.assertTrue(linkedinLoginPage.isSignInDisabled(), "signIn button is not disabled on Error page, when Login and PW empty");
}

    /**
     * DataProvider with valid Email/Password to check Login after Logout
     */
    @DataProvider
    public Object[][] invalidBackDataProvider() {
        return new Object[][]{
                { email, PW}
        };
    }
    /**
     * Verify successful user Login.
     *
     * Preconditions:
     * - Open new browser.
     * - Navigate to linkedin.com
     *
     * Scenario:
     * - Verify that login page is loaded.
     * - Enter userEmail.
     * - Enter userPassword.
     * - Click on 'Sign in' button.
     * - Verify Home page is loaded.
     * - Click on 'Logout'  button.
     * - Click on Back arrow.
     * - Verify that user is not logged, Login page is loaded.
     *  @param userEmail
     *  @param userPW
     */
    @Test(dataProvider = "invalidBackDataProvider")
    public void negativeLogoutBackLoginTest (String userEmail, String userPW) {

       LinkedinLoginPage linkedinLoginPage =  new LinkedinLoginPage(driver);
      // Assert.assertTrue(linkedinLoginPage.isPageLoaded(),"Login page is not loaded");
       LinkedInHomePage linkedInHomePage =  linkedinLoginPage.login(userEmail,userPW);
      // Assert.assertTrue(linkedInHomePage.isPageLoaded(),"Home page is not loaded");
       linkedInHomePage.clickSignOut();

       driver.navigate().back();

      // Assert.assertTrue(linkedinLoginPage.isPageLoaded(),"Login page is not loaded");
    }

}
