package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import static java.lang.Thread.sleep;


/**
 * LinkedinLogin Page Object class.
 */
public class LinkedinLoginPage extends LinkedinBasePage {

    String url ="https://www.linkedin.com/";
    String title = "LinkedIn: Log In or Sign Up" ;

    @FindBy(xpath = "//input[@class='login-email']")
    private WebElement userEmailField;
    @FindBy(xpath = "//input[@class='login-password']")
    private WebElement userPasswordField;
    @FindBy(xpath = "//input[@class='login submit-button']")
    private WebElement signInButton;
    @FindBy(xpath = "//div[@role='alert']")
    private WebElement alertMessage;
    @FindBy(xpath = "//button[@id='dismiss-alert']")
    private WebElement cookiesAlertClose;

    @FindBy(xpath = "//a[@class='link-forgot-password']")
    private WebElement forgotPasswordButton;


    /**
     * Constructor for LinkedinLoginPage
     *
     * @param driver -driver instance from tests.
     */
    public LinkedinLoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    /**
     * User login by username/password.
     *
     * @param userEmail - String with user Email.
     * @param userPW - String with user password
     * @param <T> - generic type to to return different PageObjects.
     * @return one of corresponding PageObjects LinkedInHomePage/LinkedInErrorPage/LinkedinLoginPage.
     */
    public <T> T login(String userEmail, String userPW) {//problem will occur, as can be different pages
        if (alertMessage.getText().contains("uses cookies")) {
            cookiesAlertClose.click();
        }
        userEmailField.sendKeys(userEmail);
        userPasswordField.sendKeys(userPW);
        signInButton.click();
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (getCurrentUrl().contains("/feed")) {
            return (T) new LinkedInHomePage(driver);
        }
        if (getCurrentUrl().contains("/login-submit")) {
            return (T) new LinkedInErrorPage(driver);
        }
        else {
            return (T) this;
        }
    }

    public boolean isPageLoaded() {
        return getCurrentUrl().equals(url)
                && getCurrentTitle().equals(title)
                && signInButton.isDisplayed();
    }

    public boolean isSignInDisabled() {
        return isPageLoaded()
                && (!signInButton.isEnabled()) ;
    }

    public void clearEmail() {
        userEmailField.clear();
    }

    public LinkedinForgotPasswordPage forgotPasswordClick() {
        forgotPasswordButton.click();
        return new LinkedinForgotPasswordPage(driver);
    }
}


