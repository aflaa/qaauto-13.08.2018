package page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.ArrayList;
import java.util.List;

public class LinkedinSearchPage extends LinkedinBasePage {

    String url = "https://www.linkedin.com/search/results/";
    String title = "Search | LinkedIn";

//    @FindBy(xpath = "//*[@class='search-filters-bar display-flex align-items-center']")
//    private WebElement searchResultBlock;

    @FindBy(xpath = "//h3[contains(@class,'search-results__total')]")
    private WebElement searchResultsTotal;

    @FindBy(xpath = "//li[contains(@class, 'search-result__occluded-item')]")
    private List<WebElement> searchResults;

        public LinkedinSearchPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        waitUntilElementVisible(searchResultsTotal, 10);
    }

    public boolean isPageLoaded() {
        return getCurrentUrl().contains(url)
                && getCurrentTitle().contains(title)
                && searchResultsTotal.isEnabled();
    }

    public int getSearchResultsNumber() {
//        JavascriptExecutor executor = (JavascriptExecutor)driver;
//        executor.executeScript("document.body.style.zoom = '0.8'");
//
//        JavascriptExecutor js = ((JavascriptExecutor) driver);
//        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
         return searchResults.size();
    }

    public List<String> getSearchReasultList () {
        List<String> SearchReasultList = new ArrayList<String>(); //must be initialisated to use add method later
            for (WebElement searchResult : searchResults) {
               ((JavascriptExecutor)driver).executeScript("arguments[0].scrollIntoView();", searchResult); //scroll to each searchResult
                SearchReasultList.add(searchResult.getText());
            }
            return SearchReasultList;
    }
}
