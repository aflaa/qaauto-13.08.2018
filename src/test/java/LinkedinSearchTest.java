import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class LinkedinSearchTest extends LinkedinBasePage {

    String testSite="https://www.linkedin.com/";
    String userEmail="altestqa@gmail.com";
    String userPassword="21122112";

    WebDriver driver;
    LinkedinLoginPage linkedinLoginPage;

    @BeforeMethod //BeforeTest doesn't work as expected
    public void beforeMethod() {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setExperimentalOption("useAutomationExtension",false);
        chromeOptions.addArguments("start-maximized");
        driver =new ChromeDriver(chromeOptions);
        driver.get(testSite);
        linkedinLoginPage =  new LinkedinLoginPage(driver);


    }

    @AfterMethod
    public void afterMethod() {
        driver.quit();
    }

    @DataProvider
    public Object[][] searchDataProvider() {
        return new Object[][]{
                { "hr" , 10 }
        };
    }

    @Test(dataProvider = "searchDataProvider")
    public void searchHomePageTest (String searchTerm, int countExpectedResults) {
        //navigate to linkedin.com
        //verify that login page is loaded
        //enter valid userEmail
        //enter valid userPW
        //click on 'Sign in' button
        //verify Home page is displayed

        //search for 'hr'
        //verify Search Page is loaded
        //verify 10 results on search page
        //verify each result item contains

//        JavascriptExecutor executor = (JavascriptExecutor)driver;
//        executor.executeScript("document.body.style.zoom = '1.5'");

        Assert.assertTrue(linkedinLoginPage.isPageLoaded(),"Login page is not loaded");
        LinkedInHomePage linkedInHomePage =  linkedinLoginPage.login(userEmail,userPassword);

        Assert.assertTrue(linkedInHomePage.isPageLoaded(),"Home page is not loaded");

        LinkedinSearchPage linkedinSearchPage=linkedInHomePage.search(searchTerm);
        Assert.assertTrue(linkedinSearchPage.isPageLoaded(),"Search page is not loaded");

        Assert.assertEquals(linkedinSearchPage.searchResultsCount(), 4 //countExpectedResults
                , "Found results count doesn't equal " +countExpectedResults) ;

        Assert.assertTrue(linkedinSearchPage.foundResult(searchTerm), "Search term "+ searchTerm + "is missing in search result") ;

    }


}
