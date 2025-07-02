package demo;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.logging.Level;


// import io.github.bonigarcia.wdm.WebDriverManager;
import demo.wrappers.Wrappers;
import dev.failsafe.internal.util.Assert;

public class TestCases {
    ChromeDriver driver;

    /*
     * TODO: Write your tests here with testng @Test annotation. 
     * Follow `testCase01` `testCase02`... format or what is provided in instructions
     */

     
    /*
     * Do not change the provided methods unless necessary, they will help in automation and assessment
     */
    @BeforeTest
    public void startBrowser()
    {
        System.setProperty("java.util.logging.config.file", "logging.properties");

        // NOT NEEDED FOR SELENIUM MANAGER
        // WebDriverManager.chromedriver().timeout(30).setup();

        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        logs.enable(LogType.BROWSER, Level.ALL);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);
        options.addArguments("--remote-allow-origins=*");

        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "build/chromedriver.log"); 

        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
    }


    @Test
    public void testCase01() throws InterruptedException{
        System.out.println("Start TestCase01 ");  
        double starRating=4.0;
        driver.get("https://www.flipkart.com/");        
        Wrappers.enterText(driver, By.xpath("//input[@name='q']"), "Washing Machine");         
        Thread.sleep(2000);        
        Wrappers.clickButton(driver, By.xpath("//div[@class='zg-M3Z' and text()='Popularity']"));                
        Thread.sleep(2000);        
        Wrappers.searchStarRating(driver, By.xpath("//div[@class='_5OesEi']//span//div"), starRating);                
        System.out.println("End TestCase01 ");          
    }

    @Test
    public void testCase02() throws InterruptedException{
        System.out.println("Start TestCase02 ");   
        driver.get("https://www.flipkart.com/");
        Thread.sleep(2000);
        Wrappers.enterText(driver, By.xpath("//input[@name='q']"), "iPhone");         
        Thread.sleep(2000);                         
        int discountpercent=17;      
        Wrappers.printTitleandDiscount(driver, By.xpath("//div[contains(@class,'yKfJKb')]"), discountpercent);
        System.out.println("End TestCase02 "); 
    }
       
    @Test
    public void testCase03() throws InterruptedException{
        System.out.println("Start TestCase03 ");   
        driver.get("https://www.flipkart.com/");
        Thread.sleep(2000);
        Wrappers.enterText(driver, By.xpath("//input[@name='q']"), "Coffee Mug");         
        Thread.sleep(2000);                        
        Wrappers.clickButton(driver, By.xpath("//div[contains(text(),'4')]"));
        Thread.sleep(2000);
        Wrappers.printTitleImageURL(driver, By.xpath("//div[contains(@class,'slAVV4')]//span[@class='Wphh3N']"));        
        System.out.println("End TestCase03 "); 
    }

    @AfterTest
    public void endTest()
    {
        driver.close();
        driver.quit();

    }
}

