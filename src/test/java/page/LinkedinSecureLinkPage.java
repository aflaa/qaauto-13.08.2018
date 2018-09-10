package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import static java.lang.Thread.sleep;

public class LinkedinSecureLinkPage extends LinkedinBasePage {

    String url ="/request-password-reset-submit";
    String title = "Please check your mail for reset password link" ;
   // String userEmail = "altestqa@gmail.com";

    @FindBy(xpath = "//a[@class='different__email different__email--desktop']")
    private WebElement diffEmailButton;

//    @FindBy(xpath = "//button[@id='reset-password-submit-button']")
//    private WebElement findAccountButton;
//    @FindBy(xpath= "//input[@id='username']")
//    private WebElement emailAccountField;

    public LinkedinSecureLinkPage(WebDriver driver){
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public boolean isPageLoaded() {
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return getCurrentUrl().contains(url)
                && getCurrentTitle().contains(title)
                //&& diffEmailButton.isDisplayed()
                ;
    }
}
