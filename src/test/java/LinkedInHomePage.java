import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LinkedInHomePage {

    private WebDriver driver;

    String url = "https://www.linkedin.com/feed/";
    String title = "LinkedIn" ;

    @FindBy(xpath = "//li[@id='profile-nav-item']")
    private WebElement profileNavItem;
    @FindBy(xpath = "//a[@data-control-name='nav.settings_signout']")
    private WebElement signOutButton;

    public LinkedInHomePage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }

    public String getCurrentTitle()
    {
        return driver.getTitle();
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
}
