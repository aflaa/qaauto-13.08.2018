import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LinkedInErrorPage {
    String signInButton_xpath="//input[@class='login submit-button']";
    String loginField_xpath="//input[@class='login-email']";
    String pwField_xpath="//input[@class='login-password']";

    WebDriver driver;
    WebElement userEmailField;
    WebElement userPasswordField;
    WebElement signInButton;

    public LinkedInErrorPage(WebDriver driver){
        this.driver = driver;
        initElements();
    }

    public void initElements() {
        userEmailField =driver.findElement(By.xpath(loginField_xpath));
        userPasswordField = driver.findElement(By.xpath(pwField_xpath));
        signInButton = driver.findElement(By.xpath(signInButton_xpath));
    }
}
