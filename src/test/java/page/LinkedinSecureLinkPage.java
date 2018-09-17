package page;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * LinkedinSecureLink Page Object.
 */
public class LinkedinSecureLinkPage extends LinkedinBasePage {

    String url ="/request-password-reset-submit";
    String title = "Please check your mail for reset password link" ;

    @FindBy(xpath = "//a[@class='different__email different__email--desktop']")
    private WebElement diffEmailButton;

    /**
     * Constructor of LinkedinSecureLinkPage.
     *
     * Initiate variables with Page Factory, when they are called.
     * @param driver - driver instance from tests.
     */
    public LinkedinSecureLinkPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
        assertElementIsVisible(diffEmailButton,5, "LinkedIn Secure Link Page is not loaded." );
    }
    /**
     * isPageLoaded method - checks URL, title and Different email button are found and as expected.
     * @return true, when everything found.
     */
    public boolean isPageLoaded() {
        waitUntilElementVisible(diffEmailButton, 30);
        return getCurrentUrl().contains(url)
                && getCurrentTitle().contains(title)
                && diffEmailButton.isDisplayed()
                ;
    }

    /**
     * navigateToLinkFromEmail method take link from email.
     *
     * Scenario:
     * - in email finds a link between 2 strings.
     * - Print found link.
     * - Get link to driver.
     * @return LinkedinSetNewPasswordPage
     */
    public LinkedinSetNewPasswordPage navigateToLinkFromEmail() {
        String resetPasswordLink =
                StringUtils.substringBetween(receivedEmail,
                        "To change your LinkedIn password, click <a href=\"", //escaping for " is \
                        "\" style").replace("amp;","");

        System.out.println(resetPasswordLink);
        driver.get(resetPasswordLink);
        return new LinkedinSetNewPasswordPage(driver);
    }
}
