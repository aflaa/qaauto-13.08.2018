import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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


    public LinkedInErrorPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public boolean isPageLoaded() {
        return getCurrentUrl().equals(url)
                && getCurrentTitle().equals(title)
                && signInButton.isDisplayed() ;
    }

    public String getAlertMessageText() {
        return alertMessage.getText();
    }

    public String getUserEmailAlertText() {
        return userEmailAlertMessage.getText();
    }

    public String getPWAlertText() {
        return userPasswordAlertMessage.getText();
    }
}
