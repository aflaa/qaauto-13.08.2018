import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LinkedInHomePage {
    private String signOutButton_xpath="//a[@data-control-name='nav.settings_signout']";
    private WebDriver driver;
    private WebElement profileNavItem;

    public LinkedInHomePage(WebDriver driver){
        this.driver = driver;
        initElements();
    }

    public void initElements() {
        profileNavItem = driver.findElement(By.xpath("//li[@id='profile-nav-item']"));
    }

    public boolean isDisplayed (WebElement element) {
       return element.isDisplayed();
    }

    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }

    public String getCurrentTitle(){
        return driver.getTitle();
    }

    public boolean isPageLoaded() {
        return getCurrentUrl().equals("https://www.linkedin.com/feed/")
                && getCurrentTitle().equals("LinkedIn")
                && profileNavItem.isDisplayed() ;
    }

    public void clickSignOut () {
        profileNavItem.click();
        driver.findElement(By.xpath(signOutButton_xpath)).click();
    }
}
