package test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;

public class Browser {
    char browserName = 'f';
    WebDriver driver;


    public void setBrowser() {
        switch (browserName) {
            case 'f':
                WebDriverManager.firefoxdriver().setup();
                driver = new FirefoxDriver();
                break;

            case 'c':
                ChromeOptions chromeOptions = new ChromeOptions();
                chromeOptions.setExperimentalOption("useAutomationExtension", false);
                chromeOptions.addArguments("start-maximized");
                driver = new ChromeDriver(chromeOptions);
                break;
            case 'i':
                WebDriverManager.iedriver().setup();
                driver = new InternetExplorerDriver();
                break;
            default:
                System.out.println(browserName +" name of browser is not found");

        }

    }

    ;
}
