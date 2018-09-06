import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

public class LinkedInHomePage extends LinkedinBasePage{

    String url = "https://www.linkedin.com/feed/";
    String title = "LinkedIn" ;

    @FindBy(xpath = "//li[@id='profile-nav-item']")
    private WebElement profileNavItem;
    @FindBy(xpath = "//a[@data-control-name='nav.settings_signout']")
    private WebElement signOutButton;

    @FindBy(xpath = "//input[@placeholder and @role='combobox']")
    private WebElement searchField;

    @FindBy(xpath = "//button[@aria-controls='launchpad-cards']")
    private WebElement launchpadCards;

    public LinkedInHomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public boolean isPageLoaded() {
        return getCurrentUrl().equals(url)
                && getCurrentTitle().contains(title) //can be removed
                && profileNavItem.isDisplayed() ;
    }

    public void clickSignOut () {
        profileNavItem.click();
        signOutButton.click();
    }

    public LinkedinSearchPage search(String searchTerm) {
        //launchpadCards.click(); //close a big block before search results to see more results

        searchField.sendKeys(searchTerm);
        searchField.sendKeys(Keys.ENTER); //is needed?
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new LinkedinSearchPage(driver);
    }

}
