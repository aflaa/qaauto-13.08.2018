import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

public class LinkedinLoginPage {

//    String signInButton_xpath="//input[@class='login submit-button']";
//    String loginField_xpath="//input[@class='login-email']";
//    String pwField_xpath="//input[@class='login-password']";


    private WebDriver driver;

    @FindBy(xpath = "//input[@class='login-email']")
    private WebElement userEmailField;

    @FindBy(xpath = "//input[@class='login-password']")
    private WebElement userPasswordField;

    @FindBy(xpath = "//input[@class='login submit-button']")
    private WebElement signInButton;

    public LinkedinLoginPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public <T> T login(String userEmail, String userPW) {//problem will occur, as can be different pages
        userEmailField.sendKeys(userEmail);
        userPasswordField.sendKeys(userPW);
        signInButton.click();
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (getCurrentUrl().contains("/feed")) {
            return (T) new LinkedInHomePage(driver);
        }
        if (getCurrentUrl().contains("/login-submit")) {
            return (T) new LinkedInErrorPage(driver);
        }
        else {
            return (T) this;
        }
    }

    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }

    public String getCurrentTitle(){
        return driver.getTitle();
    }

    public boolean isPageLoaded() {
        return getCurrentUrl().equals("https://www.linkedin.com/")
                && getCurrentTitle().equals("LinkedIn: Log In or Sign Up")
                && signInButton.isDisplayed();
    }

    public boolean isSignInDisabled() {
        return isPageLoaded()
                && (!signInButton.isEnabled()) ;
    }

    public void clearEmail() {
        userEmailField.clear();
    }

}
