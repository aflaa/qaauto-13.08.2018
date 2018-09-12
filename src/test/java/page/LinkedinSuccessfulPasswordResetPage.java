package page;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedinSuccessfulPasswordResetPage extends LinkedinBasePage {

    String url ="https://www.linkedin.com/checkpoint/rp/password-reset-submit";
    String title = "You've successfully reset your password. | LinkedIn" ;

    @FindBy(xpath = "//button[text()='Go to homepage']")
    private WebElement goHomeButton;

    public LinkedinSuccessfulPasswordResetPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isPageLoaded() {
        return getCurrentUrl().contains(url)
                && getCurrentTitle().contains(title)
                && goHomeButton.isDisplayed();
    }

    public LinkedInHomePage goHome() {
        goHomeButton.click();
        return new LinkedInHomePage(driver);
    }

}
