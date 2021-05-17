package java;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class SeleniumTest {


    @Test
    public void OpenBrowser()  {
        WebDriver driver;
        System.setProperty("webdriver.chrome.driver", "/usr/bin/chromedriver");
        ChromeOptions options = new ChromeOptions();
//        options.addArguments("headless");
options.addArguments("--headless", "--disable-gpu", "--window-size=1920,1200","--ignore-certificate-errors","--disable-extensions","--no-sandbox","--disable-dev-shm-usage");
        driver = new ChromeDriver(options);
        driver.get("https://www.google.com");
       // driver.get("http://65.2.108.33:8080/");
        System.out.println(driver.getTitle());
        Assert.assertTrue("Page title is not correct",driver.getTitle().equals("Google"));
        //Assert.assertTrue("Page title is not correct",driver.getTitle().equals("http://65.2.108.33:8080/"));
    }


}
