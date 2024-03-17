package com.shaarv70.tests;


import com.google.common.util.concurrent.Uninterruptibles;
import com.shaarv70.listener.TestListener;
import com.shaarv70.util.Config;
import com.shaarv70.util.Constants;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestContext;
import org.testng.annotations.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
@Listeners({TestListener.class})
public abstract class AbstractTest {

    protected WebDriver driver;
    private static final Logger log= LoggerFactory.getLogger(AbstractTest.class);

    @BeforeSuite
    public void setupConfig()
    {
        Config.initialize();
    }


    @BeforeTest
    public void setDriver(ITestContext text) throws MalformedURLException {
        this.driver=Boolean.parseBoolean(Config.get(Constants.GRID_ENABLED))?getRemoteDriver():getLocalDriver();
        text.setAttribute(Constants.DRIVER,this.driver);
        this.driver.manage().window().maximize();
    }

    private WebDriver getRemoteDriver() throws MalformedURLException {
        Capabilities capabilities= new ChromeOptions();
        if(Constants.FIREFOX.equalsIgnoreCase(Config.get(Constants.BROWSER)))
        {
            capabilities=new FirefoxOptions();
        }
        else {
            capabilities=new ChromeOptions().addArguments("--no-sandbox","--disable-dev-shm-usage");
        }
        String urlFormat=Config.get(Constants.GRID_URL_FORMAT);
        String hubHost=Config.get(Constants.GRID_HUB_HOST);
        String url=String.format(urlFormat,hubHost);
        log.info("grid url: {}",url);
        return new RemoteWebDriver(new URL(url),capabilities);

    }
    private WebDriver getLocalDriver()
    {
        return new ChromeDriver();
    }

    @AfterTest
    public void quitDriver(){
        this.driver.quit();
    }

    /*@AfterMethod
    public void sleep(){
        Uninterruptibles.sleepUninterruptibly(Duration.ofSeconds(5));
    }*/

}
