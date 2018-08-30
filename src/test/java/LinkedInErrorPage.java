import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static java.lang.Thread.sleep;

public class LinkedInErrorPage {
    String signInButton_xpath="//input[@name='signin']"; //"//input[@class='login submit-button']";
    String loginField_xpath="//input[@id='session_key-login']";
    String pwField_xpath="//input[@id='session_password-login']";
    String useCookiesAlertClose="//button[@id='dismiss-alert']";

    WebElement alertMessage;
    WebElement loginHintMessage;
    WebElement passwordHintMessage;

    private WebDriver driver;
    private WebElement userEmailField;
    private WebElement userPasswordField;
    private WebElement signInButton;


    public LinkedInErrorPage(WebDriver driver){
        this.driver = driver;
        initElements();
    }

    public void initElements() {
        userEmailField =driver.findElement(By.xpath(loginField_xpath));
        userPasswordField = driver.findElement(By.xpath(pwField_xpath));
        signInButton = driver.findElement(By.xpath(signInButton_xpath));

        alertMessage = driver.findElement(By.xpath("//div[@role='alert']"));
        loginHintMessage =driver.findElement(By.xpath("//span[@id='session_key-login-error']"));
        passwordHintMessage =driver.findElement(By.xpath("//span[@id='session_password-login-error']"));
    }

    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }

    public String getCurrentTitle(){
        return driver.getTitle();
    }

    public boolean isPageLoaded() {
        return getCurrentUrl().equals("https://www.linkedin.com/uas/login-submit")
                && getCurrentTitle().equals("Sign In to LinkedIn")
                && signInButton.isDisplayed() ;
    }

    public boolean isSignInDisabled() {
        return isPageLoaded()
        && (!signInButton.isEnabled()) ;
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

    public String getText (WebElement elmnt) {
        return elmnt.getText();

    }

    public void clearEmail() {
        userEmailField.clear();
    }


    public boolean isShortErrorMessagesMatch () {
        if (alertMessage.getText().contains("uses cookies")) {
            driver.findElement(By.xpath(useCookiesAlertClose)).click();
        }
        return getText(alertMessage).equals("There were one or more errors in your submission. Please correct the marked fields below.")
                && getText(loginHintMessage).equals("Please enter a valid email address.")
                && getText(passwordHintMessage).equals("The password you provided must have at least 6 characters.");
    }

    public boolean isLongErrorMessagesMatch () {
        if (alertMessage.getText().contains("uses cookies")) {
            driver.findElement(By.xpath(useCookiesAlertClose)).click();
        }
        return getText(alertMessage).equals("There were one or more errors in your submission. Please correct the marked fields below.")
                && getText(loginHintMessage).equals("The text you provided is too long (the maximum length is 128 characters, your text contains 129 characters).")
              ;
    }

    public boolean isWrongLoginErrorMessageMatch() {
        if (alertMessage.getText().contains("uses cookies")) {
            driver.findElement(By.xpath(useCookiesAlertClose)).click();
        }
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
       // System.out.println( alertMessage.getText());
       // System.out.println(loginHintMessage.getText());
        return
//                alertMessage.getText().equals("There were one or more errors in your submission. Please correct the marked fields below.")
//                &&
                loginHintMessage.getText().equals("Hmm, we don't recognize that email. Please try again.")
                ;
    }

    public boolean isWrongPwErrorMessageMatch() {
        if (alertMessage.getText().contains("uses cookies")) {
            driver.findElement(By.xpath(useCookiesAlertClose)).click();
        }
        return
                //alertMessage.getText().equals("There were one or more errors in your submission. Please correct the marked fields below.")
        //       getText(passwordHintMessage).equals("Hmm, that's not the right password. Please try again or request a new one.")
        passwordHintMessage.getText().equals("Hmm, that's not the right password. Please try again or request a new one.");

    }
}
