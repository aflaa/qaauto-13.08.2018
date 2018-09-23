package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


/**
 * LinkedinLogin Page Object class.
 */
public class LinkedinLoginPage extends LinkedinBasePage {

    String url =".linkedin.com/";
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
     * Constructor of LinkedinLoginPage.
     *
     * Initiate variables with Page Factory, when they are called.
     * @param driver - driver instance from tests.
     */
    public LinkedinLoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        assertElementIsVisible(signInButton,5, "LinkedIn Login Page is not loaded." );
    }

    /**
     * User login by username/password.
     *
     * @param userEmail - String with user Email.
     * @param userPW - String with user password
     * @param <T> - generic type to to return different PageObjects.
     * @return one of corresponding PageObjects LinkedInHomePage/LinkedInErrorPage/LinkedinLoginPage.
     */
    public <T> T login(String userEmail, String userPW) {//a problem will occur, as pages can be different
        if (alertMessage.getText().contains("uses cookies")) {
            cookiesAlertClose.click();
        }
        userEmailField.sendKeys(userEmail);
        userPasswordField.sendKeys(userPW);
        signInButton.click();

        if (isUrlContains("/feed", 3)) {
            return (T) new LinkedInHomePage(driver);
        }
        if (isUrlContains("/login-submit", 3)) {
            return (T) new LinkedInErrorPage(driver);
        }
        else {
            return (T) this;
        }
    }

    /**
     * isPageLoaded method. Checks URL, title and signIn Button are found as expected.
     * @return true, when everything found.
     */
    public boolean isPageLoaded() {
        waitUntilElementVisible(signInButton, 30);
        return getCurrentUrl().contains(url)
                && getCurrentTitle().contains(title)
                && signInButton.isDisplayed();
    }

    /**
     * isSignInDisabled method checks if signInButton is disable.
     *
     * @return true when signInButton is disable.
     */
    public boolean isSignInDisabled() {
        return isPageLoaded()
                && (!signInButton.isEnabled()) ;
    }

    /**
     * clearEmail method clears email field.
     */
    public void clearEmail() {
        userEmailField.clear();
    }

    /**
     * forgotPasswordClick method clicks on forgotPassword button.
     *
     * @return LinkedinForgotPasswordPage.
     */
    public LinkedinForgotPasswordPage forgotPasswordClick() {
        forgotPasswordButton.click();
        return new LinkedinForgotPasswordPage(driver);
    }
}


