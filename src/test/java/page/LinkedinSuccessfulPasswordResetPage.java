package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * LinkedinSuccessfulPasswordReset Page Object.
 */
public class LinkedinSuccessfulPasswordResetPage extends LinkedinBasePage {

    String url =".linkedin.com/checkpoint/rp/password-reset-submit";
    String title = "You've successfully reset your password. | LinkedIn" ;

    @FindBy(xpath = "//button[text()='Go to homepage']")
    private WebElement goHomeButton;

    /**
     * Constructor of LinkedinSuccessfulPasswordResetPage.
     *
     * Initiate variables with Page Factory, when they are called.
     * @param driver - driver instance from tests.
     */
    public LinkedinSuccessfulPasswordResetPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        assertElementIsVisible(goHomeButton,5, "LinkedIn Successful Reset Password Page is not loaded." );
    }

    /**
     * isPageLoaded method - checks URL, title and go home button are found and as expected.
     * @return true, when everything found.
     */
    public boolean isPageLoaded() {
        return getCurrentUrl().contains(url)
                && getCurrentTitle().contains(title)
                && goHomeButton.isDisplayed();
    }

    /**
     * goHome method - clicks on goHome button.
     * @return new LinkedInHomePage.
     */
    public LinkedInHomePage goHome() {
        goHomeButton.click();
        return new LinkedInHomePage(driver);
    }

}
