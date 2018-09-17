package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


/**
 * LinkedinForgotPasswordPage Page Object.
 */
public class LinkedinForgotPasswordPage extends LinkedinBasePage {

    String url ="https://www.linkedin.com/uas/request-password-reset";
    String title = "Reset Password | LinkedIn" ;
    String userEmail = "altestqa@gmail.com";

    @FindBy(xpath = "//input[@class='login-email']")
    private WebElement userEmailField;
    @FindBy(xpath = "//button[@id='reset-password-submit-button']")
    private WebElement resetPasswordButton;
    @FindBy(xpath= "//input[@id='username']")
    private WebElement emailAccountField;

    /**
     * Constructor of LinkedinForgotPasswordPage.
     *
     * Initiate variables with Page Factory, when they are called.
     * @param driver - driver instance from tests.
     */
    public LinkedinForgotPasswordPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        assertElementIsVisible(emailAccountField,5, "LinkedIn Forgot Page is not loaded." );
    }

    /**
     * isPageLoaded method - checks URL, title and Reset Password button are found and as expected.
     * @return true, when everything found.
     */
    public boolean isPageLoaded() {
        return getCurrentUrl().contains(url)
                && getCurrentTitle().contains(title)
                && resetPasswordButton.isDisplayed();
    }

    /**
     * findAccount method cliks to find Account to reset password.
     *
     * - Connect to email service.
     * - Insert user email and click.
     * - Wait for a new email with reset href.
     * @return LinkedinSecureLinkPage
     */
    public LinkedinSecureLinkPage findAccount() {
        gMailService.connect();

        emailAccountField.sendKeys(userEmail);
        resetPasswordButton.click();

        String messageSubject = "here's the link to reset your password";
        String messageTo = "altestqa@gmail.com";
        String messageFrom = "security-noreply@linkedin.com";

        receivedEmail= gMailService.waitMessage(messageSubject, messageTo, messageFrom, 180);
        System.out.println("Content: " + receivedEmail );

        return new LinkedinSecureLinkPage(driver);
    }
}
