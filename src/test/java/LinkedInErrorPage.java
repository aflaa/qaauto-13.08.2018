import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

public class LinkedInErrorPage {

    private WebDriver driver;

    String url = "https://www.linkedin.com/uas/login-submit";
    String title = "Sign In to LinkedIn";

    @FindBy(xpath = "//input[@id='session_key-login']")
    private WebElement userEmailField;
    @FindBy(xpath = "//input[@id='session_password-login']")
    private WebElement userPasswordField;
    @FindBy(xpath = "//input[@name='signin']")
    private WebElement signInButton;


    @FindBy(xpath = "//div[@role='alert']")
    private WebElement alertMessage;
    @FindBy(xpath = "//span[@id='session_key-login-error']")
    private WebElement loginHintMessage ;
    @FindBy(xpath = "//span[@id='session_password-login-error']")
    private WebElement passwordHintMessage;


    public LinkedInErrorPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver,this);
    }

    public String getCurrentUrl(){

        return driver.getCurrentUrl();
    }

    public String getCurrentTitle(){
        return driver.getTitle();
    }

    public boolean isPageLoaded() {
        return getCurrentUrl().equals(url)
                && getCurrentTitle().equals(title)
                && signInButton.isDisplayed() ;
    }

    public String getText (WebElement elmnt) {
        return elmnt.getText();

    }

    public boolean isMessageMatch(String loginMessage, String pwMessage) {
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return getText(alertMessage).equals("There were one or more errors in your submission. Please correct the marked fields below.")
                && getText(loginHintMessage).equals(loginMessage)
                && getText(passwordHintMessage).equals(pwMessage);
    }
}
