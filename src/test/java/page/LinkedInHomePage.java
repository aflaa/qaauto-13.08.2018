package page;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

/**
 * LinkedInHome Page Object.
 */
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

    /**
     * Constructor of LinkedinForgotPasswordPage.
     *
     * Initiate variables with Page Factory, when they are called.
     * @param driver - driver instance from tests.
     */
    public LinkedInHomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
        assertElementIsVisible(profileNavItem,5 , "LinkedIn Home Page is not loaded." );
    }
    /**
     * isPageLoaded method. Checks URL, title and Profile navigation menu are found as expected.
     * @return true, when everything found.
     */
    public boolean isPageLoaded() {
        return getCurrentUrl().equals(url)
                && getCurrentTitle().contains(title) //can be removed
                && profileNavItem.isDisplayed() ;
    }

    /**
     * clickSignOut method is clickon Sign out item in frofile navigation menu.
     */
    public void clickSignOut () {
        profileNavItem.click();
        signOutButton.click();
    }

    /**
     * search method - insert a search word with enter.
     *
     * @param searchTerm - word is searched.
     * @return - LinkedinSearchPage with results.
     */
    public LinkedinSearchPage search(String searchTerm) {

        searchField.sendKeys(searchTerm);
        searchField.sendKeys(Keys.ENTER);

        return new LinkedinSearchPage(driver);
    }

}
