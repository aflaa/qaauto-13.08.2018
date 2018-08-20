import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import static java.lang.Thread.sleep;

public class LinkedinLoginTest {

    @Test
    public void succeedfulLoginTest () {
        //navigate to linkedin.com
        //verify that login page is loaded
        //enter userEmail
        //enter userPW
        //click on 'Sign in' button
        //verify Home page is displayed.

        String testSite="https://www.linkedin.com/";
        String email="altestqa@gmail.com";
        String PW="21122112";
        String userName="al lena";
        String signInButton_xpath="//input[@class='login submit-button']";
        String loginField_xpath="//input[@class='login-email']";
        String pwField_xpath="//input[@class='login-password']";
        String profilePhoto_xpath="//img[@class='nav-item__profile-member-photo nav-item__icon ghost-person']";
        String signOutButton_xpath="//a[@data-control-name='nav.settings_signout']";


        WebDriver driver = new ChromeDriver();
        driver.get(testSite);

        Assert.assertEquals(driver.getCurrentUrl(), testSite,"Login page URL is wrong.");
        Assert.assertEquals(driver.getTitle(), "LinkedIn: Log In or Sign Up","Login page title is wrong.");

        WebElement userEmailField=driver.findElement(By.xpath(loginField_xpath));
        WebElement userPasswordField=driver.findElement(By.xpath(pwField_xpath));
        WebElement signInButton=driver.findElement(By.xpath(signInButton_xpath));

        Assert.assertTrue(signInButton.isDisplayed(), "signIn button is not displayed on Login page.");
        //Assert.assertEquals(signInButton.getAttribute("value"), "Sign in","Sign in button is unavailable");
       // Assert.assertNotNull(driver.findElement(By.xpath("//input[@class='login submit-button']").getText()));

        //userEmailField.clear(); -not needed, as everytime browser opened by default empty
        userEmailField.sendKeys(email);
        //userPasswordField.clear();
        userPasswordField.sendKeys(PW);
        signInButton.click();

        Assert.assertEquals(driver.getCurrentUrl(), "https://www.linkedin.com/feed/","Login page URL is wrong.");
        Assert.assertEquals(driver.getTitle(), "LinkedIn","Login page title is wrong.");

//        WebElement profilePhoto=driver.findElement(By.xpath(profilePhoto_xpath));
//        Assert.assertEquals(profilePhoto.getAttribute("alt"), userName,"User name is incorrect");
//        profilePhoto.click();

        WebElement profileNavItem = driver.findElement(By.xpath("//li[@id='profile-nav-item']"));
        Assert.assertTrue(profileNavItem.isDisplayed(),
                "profileNavItem is not displayed on Home page.");

 //       driver.findElement(By.xpath(signOutButton_xpath)).click();
 //       driver.close();
    }
    @Test
    public void negativeLoginTest (){
        String testSite="https://www.linkedin.com/";
        String email="a@b.c";
        String PW="wrong";
        String signInButton_xpath="//input[@class='login submit-button']";
        String loginField_xpath="//input[@class='login-email']";
        String pwField_xpath="//input[@class='login-password']";


        WebDriver driver = new ChromeDriver();
        driver.get(testSite);

        Assert.assertEquals(driver.getCurrentUrl(), testSite,"Login page URL is wrong.");
        Assert.assertEquals(driver.getTitle(), "LinkedIn: Log In or Sign Up","Login page title is wrong.");

        WebElement userEmailField=driver.findElement(By.xpath(loginField_xpath));
        WebElement userPasswordField=driver.findElement(By.xpath(pwField_xpath));
        WebElement signInButton=driver.findElement(By.xpath(signInButton_xpath));

        Assert.assertTrue(signInButton.isDisplayed(), "signIn button is not displayed on Login page.");
        userEmailField.sendKeys(email);
        userPasswordField.sendKeys(PW);
        signInButton.click();

      //  Assert.assertEquals(driver.getCurrentUrl(), "https://www.linkedin.com/uas/login-submit","Login page URL is wrong.");
      //  Assert.assertEquals(driver.getTitle(), "Sign In to LinkedIn","Login page title is wrong.");

        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement alertMessage = driver.findElement(By.xpath("//div[@role='alert']"));
        Assert.assertEquals(alertMessage.getText(),
               "There were one or more errors in your submission. Please correct the marked fields below.",
                "Alert message test is wrong.");

    }
}
