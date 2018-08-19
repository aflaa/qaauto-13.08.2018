import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import java.util.List;

//import java.util.concurrent.TimeUnit;

public class BadCodeExample {
    public static void main(String arg[]) {
        String searchText="Selenium";
        System.out.println("Hello World!!!");

        WebDriver driver = new ChromeDriver();
        driver.get("https://www.google.com/");
       // driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);

        WebElement searchField = driver.findElement(By.xpath("//input[@id='lst-ib']")); // //input[@id='lst-ib'] or //input[@title='Search'] or //input[contains(@title,'Search')]
       //WebElement searchField = driver.findElement(By.name("q"));
       searchField.sendKeys(searchText);
       //searchField.submit(); run javascript, don't use
       searchField.sendKeys(Keys.ENTER);

        List<WebElement> searchResults=driver.findElements(By.xpath("//div[@class='srg']/div[@class='g']"));
        System.out.println("Search results count: "+ searchResults.size());

         int searchResultsCount=searchResults.size();

         if (searchResultsCount ==10) {
            System.out.println("Search results count is correct: " + searchResultsCount);
         }
         else {
            System.out.println("Search results count is incorrect: " + searchResultsCount);
        }

        for (WebElement searchResult : searchResults) {
            String searchResultsText =searchResult.getText();
            System.out.println(searchResultsText);

            if (searchResultsText.toLowerCase().contains(searchText.toLowerCase())){
                System.out.println("SearchTerm found.");
            }
            else {
                System.out.println("SearchTerm not found.");
            }
        }
        driver.close();
    }
}
