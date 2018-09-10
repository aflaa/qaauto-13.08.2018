package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import util.GMailService;

public class LinkedinForgotPasswordPage extends LinkedinBasePage {

    String url ="https://www.linkedin.com/uas/request-password-reset";
    String title = "Reset Password | LinkedIn" ;
    String userEmail = "altestqa@gmail.com";

    @FindBy(xpath = "//input[@class='login-email']")
    private WebElement userEmailField;
    @FindBy(xpath = "//button[@id='reset-password-submit-button']")
    private WebElement findAccountButton;
    @FindBy(xpath= "//input[@id='username']")
    private WebElement emailAccountField;

    public LinkedinForgotPasswordPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isPageLoaded() {
        return getCurrentUrl().contains(url)
                && getCurrentTitle().contains(title)
                && findAccountButton.isDisplayed();
    }

    public LinkedinSecureLinkPage findAccount() {
        GMailService gMailService = new GMailService();
        gMailService.connect();

        emailAccountField.sendKeys(userEmail);
        findAccountButton.click();
        //ToDo:
    //getting URL from gmail >> move to other page:
        //  take message
        //  find href
        //  encode href with &amp
        //decode href
        //insert href
        // go to insert new password

        String messageSubject = "here's the link to reset your password";
        String messageTo = "altestqa@gmail.com";
        String messageFrom = "security-noreply@linkedin.com";

        String message = gMailService.waitMessage(messageSubject, messageTo, messageFrom, 180);
        System.out.println("Content: " + message);

        return new LinkedinSecureLinkPage(driver);
    }
}
