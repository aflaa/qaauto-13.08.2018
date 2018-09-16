package test;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import page.LinkedInHomePage;
import page.LinkedinSearchPage;

import java.util.List;

/**
 * LinkedinSearch Test class
 */
public class LinkedinSearchTest extends LinkedinBaseTest {

    /**
     * DataProvider for search word/term on Home Page.
     *
     * @return Search word and Expected result number.
     */
    @DataProvider
    public Object[][] searchDataProvider() {
        return new Object[][]{
                { "hr" , 10 }
        };
    }

    /**
     * Search on HomePage Test.
     *
     * @param searchTerm - a searched word.
     * @param countExpectedResults -number of results found on page after Search submit.
     *
     * Preconditions:
     * - Open new browser.
     * - Navigate to linkedin.com
     *
     * Scenario:
     * - Verify that login page is loaded.
     * - Enter userEmail/userPassword.
     * - Click on 'Sign in' button.
     * - Verify Home page is loaded.
     * - Search for searchTerm='hr'.
     * - Verify Search Page is loaded.
     * - Verify 10 results on search page.
     * - Verify each result item contains a searchTerm.
     */
    @Test(dataProvider = "searchDataProvider")
    public void searchHomePageTest (String searchTerm, int countExpectedResults) {
        Assert.assertTrue(linkedinLoginPage.isPageLoaded(),"Login page is not loaded");
        LinkedInHomePage linkedInHomePage =  linkedinLoginPage.login(userEmail,userPW);

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
