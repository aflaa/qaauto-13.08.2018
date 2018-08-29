import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WebDriver;

import static java.lang.Thread.sleep;

public class LinkedinLoginPage {

    String signInButton_xpath="//input[@class='login submit-button']";
    String loginField_xpath="//input[@class='login-email']";
    String pwField_xpath="//input[@class='login-password']";

    private WebDriver driver;
    private WebElement userEmailField;
    private WebElement userPasswordField;
    private WebElement signInButton;

    public LinkedinLoginPage(WebDriver driver){
        this.driver = driver;
        initElements();
    }

    public void initElements() {
        userEmailField =driver.findElement(By.xpath(loginField_xpath));
        userPasswordField = driver.findElement(By.xpath(pwField_xpath));
        signInButton = driver.findElement(By.xpath(signInButton_xpath));
    }

    public void login(String userEmail, String userPW) {
        userEmailField.sendKeys(userEmail);
        userPasswordField.sendKeys(userPW);
        signInButton.click();
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
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
