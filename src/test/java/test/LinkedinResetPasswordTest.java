package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.*;

import static java.lang.Thread.sleep;

public class LinkedinResetPasswordTest extends LinkedinBaseTest{

    String email="altestqa@gmail.com";
    String newPassword = "Qqqq2222";

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

        Assert.assertTrue(linkedinForgotPasswordPage.isPageLoaded(),"Forgot password page is not loaded");

        LinkedinSecureLinkPage linkedinSecureLinkPage =linkedinForgotPasswordPage.findAccount();

        /////////////////////////////////////////////manual link click//////////////////////////

        try {
            sleep(12000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        //navigate to link from email. from 1st onject link to gmail, from other get it
        Assert.assertTrue(linkedinSecureLinkPage.isPageLoaded(),"Secure link page is not loaded");

        LinkedinSetNewPasswordPage linkedinSetNewPasswordPage =
                linkedinSecureLinkPage.navigateToLinkFromEmail();

        Assert.assertTrue(linkedinSetNewPasswordPage.isPageLoaded(),
                "SetNewPasswordPage is not loaded.");

        LinkedinSuccessfulPasswordResetPage linkedinSuccessfulPasswordResetPage
                = linkedinSetNewPasswordPage.newPasswordSend(newPassword);

        Assert.assertTrue(linkedinSuccessfulPasswordResetPage.isPageLoaded(),
                "SuccessfulPasswordResetPage is not loaded.");
        LinkedInHomePage linkedInHomePage = linkedinSuccessfulPasswordResetPage.goHome();

        Assert.assertTrue(linkedInHomePage.isPageLoaded(),"Home page is not loaded");

    }

}
