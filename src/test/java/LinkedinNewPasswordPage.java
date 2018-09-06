import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedinNewPasswordPage  extends LinkedinBasePage {

    String url ="https://www.linkedin.com/checkpoint/rp/password-reset";
    String title = "Reset Your Password | LinkedIn" ;
    String userEmail = "altestqa@gmail.com";
    String userNewPW= "qqqq2222";

    @FindBy(xpath = "//input[@id='newPassword']")
    private WebElement newPasswordField;
    @FindBy(xpath = "//input[@id='confirmPassword']")
    private WebElement confirmPwField;
    @FindBy(xpath = "//button[@id='reset-password-submit-button']")
    private WebElement submitNewPwButton;

    public LinkedinNewPasswordPage (WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isPageLoaded() {
        return getCurrentUrl().contains(url)
                && getCurrentTitle().contains(title)
                && newPasswordField.isDisplayed();
    }

    public LinkedinPasswordChangedPage newPasswordSend() {
        newPasswordField.sendKeys(userNewPW);
        confirmPwField.sendKeys(userNewPW);
        submitNewPwButton.click();
        return new LinkedinPasswordChangedPage(driver);
    }
}
