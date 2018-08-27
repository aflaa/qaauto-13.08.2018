import org.openqa.selenium.WebDriver;

public class LinkedInHomePage {
    WebDriver driver;

    public LinkedInHomePage(WebDriver driver){
        this.driver = driver;
      //  initElements();
    }



    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }

    public String getCurrentTitle(){
        return driver.getTitle();
    }

    public boolean isPageLoaded() {
        return getCurrentUrl().equals("https://")
                && getCurrentTitle().equals("LinkedIn:")
                //&& signInButton.isDisplayed()
                ;
    }
}
