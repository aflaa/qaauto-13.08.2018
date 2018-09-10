package test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.LinkedInHomePage;
import page.LinkedinSearchPage;

import java.util.List;

public class LinkedinSearchTest extends LinkedinBaseTest {

    String userEmail="altestqa@gmail.com";
    String userPassword="21122112";

    @DataProvider
    public Object[][] searchDataProvider() {
        return new Object[][]{
                { "hr" , 10 }
        };
    }

    @Test(dataProvider = "searchDataProvider")
    public void searchHomePageTest (String searchTerm, int countExpectedResults) {
        //navigate to linkedin.com and verify that login page is loaded
        //Logon with valid userEmail/userPW and click on 'Sign in' button
        //verify Home page is displayed

        //search for searchTerm='hr'
        //verify Search Page is loaded
        //verify 10 results on search page
        //verify each result item contains a searchTerm

        Assert.assertTrue(linkedinLoginPage.isPageLoaded(),"Login page is not loaded");
        LinkedInHomePage linkedInHomePage =  linkedinLoginPage.login(userEmail,userPassword);

        Assert.assertTrue(linkedInHomePage.isPageLoaded(),"Home page is not loaded");

        LinkedinSearchPage linkedinSearchPage=linkedInHomePage.search(searchTerm);
        Assert.assertTrue(linkedinSearchPage.isPageLoaded(),"Search page is not loaded");

        Assert.assertEquals(linkedinSearchPage.getSearchResultsNumber(), countExpectedResults, "Wrong number of search results on Search Page") ;

        List<String> searchResultsList = linkedinSearchPage.getSearchReasultList();

        for (String searchResult : searchResultsList) {
            Assert.assertTrue(searchResult.toLowerCase().contains(searchTerm.toLowerCase()),
                    "SearchTerm " + searchTerm+ " not found in:\n" + searchResult);
        }
    }

}
