package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * LinkedInError Page Object.
 *
 * Page loaded after some errors were submit in Login or password fields.
 */
public class LinkedInErrorPage extends LinkedinBasePage {

    String url = "https://www.linkedin.com/uas/login-submit";
    String title = "Sign In to LinkedIn";

    @FindBy(xpath = "//input[@id='session_key-login']")
    private WebElement userEmailField;
    @FindBy(xpath = "//input[@id='session_password-login']")
    private WebElement userPasswordField;
    @FindBy(xpath = "//input[@name='signin']")
    private WebElement signInButton;

    @FindBy(xpath = "//div[@role='alert']")
    private WebElement alertMessage;
    @FindBy(xpath = "//span[@id='session_key-login-error']")
    private WebElement userEmailAlertMessage;
    @FindBy(xpath = "//*[@id='session_password-login-error']") //* -as span is usually changed to other tag
    private WebElement userPasswordAlertMessage;

    /**
     * Constructor of LinkedInErrorPage.
     *
     * Initiate variables with Page Factory, when they are called.
     * @param driver - driver instance from tests.
     */
    public LinkedInErrorPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
        assertElementIsVisible(userEmailField,5, "LinkedIn Error Page is not loaded." );
    }

    /**
     * isPageLoaded method. Find and checks URL, title and Sign in button are as expected.
     * @return true, when everything found.
     */
    public boolean isPageLoaded() {
        return getCurrentUrl().contains(url)
                && getCurrentTitle().contains(title)
               && userPasswordField.isDisplayed()
                ;
    }

    /**
     * getAlertMessageText method - get alert text on page.
     * @return
     */
    public String getAlertMessageText() {
        return alertMessage.getText();
    }

    /**
     * getUserEmailAlertText method - get alert message for user email field.
     * @return
     */
    public String getUserEmailAlertText() {
        return userEmailAlertMessage.getText();
    }

    /**
     * getAlertMessageText method - get alert message for password field.
     * @return
     */
    public String getPWAlertText() {
        return userPasswordAlertMessage.getText();
    }
}
