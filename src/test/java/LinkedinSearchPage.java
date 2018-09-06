import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

import static java.lang.Thread.sleep;

public class LinkedinSearchPage extends LinkedinBasePage {

    String url = "https://www.linkedin.com/search/results/";
    String title = "Search | LinkedIn";

    @FindBy(xpath = "//*[@class='search-filters-bar display-flex align-items-center']")
    private WebElement searchResultBlock;

    @FindBy(xpath = "//div[@class='search-result__info pt3 pb4 ph0']") ////li[@class='search-result search-result__occluded-item ember-view']")
    private List<WebElement> foundResults;

    public LinkedinSearchPage(WebDriver driver) {
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
                && searchResultBlock.isEnabled();
    }

    public int searchResultsCount() {
        JavascriptExecutor executor = (JavascriptExecutor)driver;
        executor.executeScript("document.body.style.zoom = '0.8'");

        JavascriptExecutor js = ((JavascriptExecutor) driver);
        js.executeScript("window.scrollTo(0, document.body.scrollHeight)");
        try {
            sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return foundResults.size();
    }

    public boolean foundResult(String searchTerm) {
        int count=0;
        for (WebElement searchResult : foundResults) {
            String searchResultsText = searchResult.getText();
            if (!searchResultsText.toLowerCase().contains(searchTerm.toLowerCase())) {
                count=count+1;
            }
        }
        return count==0;
    }

}
