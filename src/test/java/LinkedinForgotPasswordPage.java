import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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
        emailAccountField.sendKeys(userEmail);
        findAccountButton.click();
        return new LinkedinSecureLinkPage(driver);
    }
}
