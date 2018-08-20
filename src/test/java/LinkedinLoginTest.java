import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

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

        WebElement signButton=driver.findElement(By.xpath(signInButton_xpath));
        Assert.assertEquals(signButton.getAttribute("value"), "Sign in","Sign in button is unavailable");
        //Assert.assertEquals(signButton.getText(), "Sign in","Sign in button is unavailable");
       // Assert.assertNotNull(driver.findElement(By.xpath("//input[@class='login submit-button']").getText()));


        WebElement loginEmail=driver.findElement(By.xpath(loginField_xpath));
        loginEmail.clear();
        loginEmail.sendKeys(email);

        WebElement loginPW=driver.findElement(By.xpath(pwField_xpath));
        loginPW.clear();
        loginPW.sendKeys(PW);

        signButton.click();

        WebElement profilePhoto=driver.findElement(By.xpath(profilePhoto_xpath));
        Assert.assertEquals(profilePhoto.getAttribute("alt"), userName,"User name is incorrect");
        profilePhoto.click();

        driver.findElement(By.xpath(signOutButton_xpath)).click();
        driver.close();
    }
}
