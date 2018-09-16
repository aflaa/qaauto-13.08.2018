package test;

import org.testng.Assert;
import org.testng.annotations.Test;
import page.*;
/**
 * LinkedinReset Password Test class.
 */
public class LinkedinResetPasswordTest extends LinkedinBaseTest{

    String email="altestqa@gmail.com";
    String newPassword = "Aqqq2222";

    /**
     * Verify user Login with empty email and/or password.
     *
     *  Preconditions:
     *  Open new browser.
     *  Navigate to linkedin.com
     *
     * Scenario:
     * - Verify that login page is loaded.
     * - Click Reset Password.
     * - Verify that Forgot PW page is loaded.
     * - Fill in Email/PW to get link for new password.
     * - Verify that Submit page is loaded.
     * - Connect to email  to copy link sent.
     * - Navigate to link from email.
     * - Verify that new password page is loaded.
     * - Fill in new password twice.
     * - Verify that home page is loaded.
     */
    @Test
    public void resetPasswordTest () {
        Assert.assertTrue(linkedinLoginPage.isPageLoaded(),"Login page is not loaded");
        LinkedinForgotPasswordPage linkedinForgotPasswordPage = linkedinLoginPage.forgotPasswordClick();

        Assert.assertTrue(linkedinForgotPasswordPage.isPageLoaded(),"Forgot password page is not loaded");

        LinkedinSecureLinkPage linkedinSecureLinkPage =linkedinForgotPasswordPage.findAccount();

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
