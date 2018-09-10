package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.*;

import static java.lang.Thread.sleep;

public class LinkedinResetPasswordTest extends LinkedinBaseTest{

    String email="altestqa@gmail.com";
    String PW="21122112";

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

        //navigate to link from email. from 1st onject link to gmail, from other get it
        LinkedinNewPasswordPage linkedinNewPasswordPage =new LinkedinNewPasswordPage(driver);

        LinkedinPasswordChangedPage linkedinPasswordChangedPage = linkedinNewPasswordPage.newPasswordSend();
        LinkedInHomePage linkedInHomePage = linkedinPasswordChangedPage.goHome();

        Assert.assertTrue(linkedInHomePage.isPageLoaded(),"Home page is not loaded");

    }

}
