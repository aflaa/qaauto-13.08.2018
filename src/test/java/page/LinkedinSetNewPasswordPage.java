package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedinSetNewPasswordPage extends LinkedinBasePage {

    String url ="https://www.linkedin.com/checkpoint/rp/password-reset";
    String title = "Reset Your Password | LinkedIn" ;

    @FindBy(xpath = "//input[@id='newPassword']")
    private WebElement newPasswordField;
    @FindBy(xpath = "//input[@id='confirmPassword']")
    private WebElement confirmPwField;
    @FindBy(xpath = "//button[@id='reset-password-submit-button']")
    private WebElement submitNewPwButton;

    public LinkedinSetNewPasswordPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isPageLoaded() {
        return getCurrentUrl().contains(url)
                && getCurrentTitle().contains(title)
                && newPasswordField.isDisplayed();
    }

    public LinkedinSuccessfulPasswordResetPage newPasswordSend(String userNewPW) {
        newPasswordField.sendKeys(userNewPW);
        confirmPwField.sendKeys(userNewPW);
        submitNewPwButton.click();
        return new LinkedinSuccessfulPasswordResetPage(driver);
    }

}
