package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * LinkedinSetNewPassword Page Object.
 */
public class LinkedinSetNewPasswordPage extends LinkedinBasePage {

    String url =".linkedin.com/checkpoint/rp/password-reset";
    String title = "Reset Your Password | LinkedIn" ;

    @FindBy(xpath = "//input[@id='newPassword']")
    private WebElement newPasswordField;
    @FindBy(xpath = "//input[@id='confirmPassword']")
    private WebElement confirmPwField;
    @FindBy(xpath = "//button[@id='reset-password-submit-button']")
    private WebElement submitNewPwButton;

    /**
     * Constructor of LinkedinSetNewPasswordPage.
     *
     * Initiate variables with Page Factory, when they are called.
     * @param driver - driver instance from tests.
     */
    public LinkedinSetNewPasswordPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        assertElementIsVisible(newPasswordField,5, "LinkedIn New Password Page is not loaded." );
    }

    /**
     * isPageLoaded method - checks URL, title and New Password button are found and as expected.
     * @return true, when everything found.
     */
    public boolean isPageLoaded() {
        return getCurrentUrl().contains(url)
                && getCurrentTitle().contains(title)
                && newPasswordField.isDisplayed();
    }

    /**
     * newPasswordSend method fill in anew password.
     * @param userNewPW -Stringwith new password.
     * @return LinkedinSuccessfulPasswordResetPage.
     */
    public LinkedinSuccessfulPasswordResetPage newPasswordSend(String userNewPW) {
        newPasswordField.sendKeys(userNewPW);
        confirmPwField.sendKeys(userNewPW);
        submitNewPwButton.click();
        return new LinkedinSuccessfulPasswordResetPage(driver);
    }

}
