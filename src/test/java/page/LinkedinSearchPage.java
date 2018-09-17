package page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * LinkedinSearch Page Object.
 */
public class LinkedinSearchPage extends LinkedinBasePage {

    String url = "https://www.linkedin.com/search/results/";
    String title = "Search | LinkedIn";

    @FindBy(xpath = "//h3[contains(@class,'search-results__total')]")
    private WebElement searchResultsTotal;

    @FindBy(xpath = "//li[contains(@class, 'search-result__occluded-item')]")
    private List<WebElement> searchResults;

    /**
     * Constructor of LinkedinSearchPage.
     *
     * Initiate variables with Page Factory, when they are called.
     * @param driver - driver instance from tests.
     */
    public LinkedinSearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        assertElementIsVisible(searchResultsTotal,5, "LinkedIn Search Page is not loaded." );
    }
    /**
     * isPageLoaded method - checks URL, title and search results count are found as expected.
     *
     * @return true, when everything found.
     */
    public boolean isPageLoaded() {
        return getCurrentUrl().contains(url)
                && getCurrentTitle().contains(title)
                && searchResultsTotal.isEnabled();
    }

    /**
     * getSearchResultsNumber method - calculates number of found results on page.
     * @return
     */
    public int getSearchResultsNumber() {
         return searchResults.size();
    }

    /**
     * getSearchReasultList method - scroll to every found result and get it's text.
     * @return String list of text results.
     */
    public List<String> getSearchReasultList () {
        List<String> SearchReasultList = new ArrayList<String>(); //must be initialized to use add method later
            for (WebElement searchResult : searchResults) {
               ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", searchResult); //scroll to each searchResult
                SearchReasultList.add(searchResult.getText());
            }
            return SearchReasultList;
    }
}
