import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedInHomePage extends LinkedinBasePage{

    String url = "https://www.linkedin.com/feed/";
    String title = "LinkedIn" ;

    @FindBy(xpath = "//li[@id='profile-nav-item']")
    private WebElement profileNavItem;
    @FindBy(xpath = "//a[@data-control-name='nav.settings_signout']")
    private WebElement signOutButton;

    @FindBy(xpath = "//*[@class='search-typeahead-v2 ember-view']//input[@placeholder='Search']")
    private WebElement searchField;

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
        searchField.sendKeys(searchTerm);
        searchField.sendKeys(Keys.ENTER); //is needed?
        return new LinkedinSearchPage(driver);
    }

}
