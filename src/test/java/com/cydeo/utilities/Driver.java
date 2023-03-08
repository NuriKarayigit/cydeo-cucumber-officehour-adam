package com.cydeo.utilities;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;

public class Driver {


    // Create a private constructor to remove access to this object
    private Driver(){

    }
    /*
    We make the WebDriver private, because we want to close access from outside the class.
    We are making it static, because we will use it in a static method.
     */
 //private static WebDriver driver; // default value = null;
private static  InheritableThreadLocal<WebDriver> driverPool = new InheritableThreadLocal<>();
    /*
    Create a re-usable utility method which will return the same driver instance once we call it.
    if an instance doesn't exist it will create first, and then it will always return same instance.
     */

    public static WebDriver getDriver(){
        if (driverPool.get()==null){
            /*
            we will read our browser type from configuration.properties file.
            this way we can control which browser is opened from outside our code
             */
            String browserType = ConfigurationReader.getProperty("browser");
            browserType=browserType.trim();
/*
Depending on the browserType returned from the configuration.properties
switch statement will determine the "case" and open the matching browser
 */
            switch (browserType){
                case "chrome":
                 //   WebDriverManager.chromedriver().setup();
                    driverPool.set(new ChromeDriver());
                    driverPool.get().manage().window().maximize();
                    driverPool.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                    break;
                case "firefox":
                 //   WebDriverManager.fireFoxDriver().setup();
                    driverPool.set(new FirefoxDriver());
                    driverPool.get().manage().window().maximize();
                    driverPool.get().manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                    break;
            }

        }

        return driverPool.get();
    }


    public static void closeDriver(){
        if (driverPool.get()!=null){
            /*
            this line will terminate the currently existing driver completely.it will be non existant
             */
            driverPool.get().quit();
            /*
            we assign the value back to null so my singleton can create a newer one if needed.
             */
            driverPool.remove();
        }
    }


}
