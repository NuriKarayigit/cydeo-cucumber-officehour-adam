package com.cydeo.step_definitions;

import com.cydeo.utilities.BrowserUtils;
import com.cydeo.utilities.Driver;
import io.cucumber.java.*;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

/*
In this class we will be able to create "pre" and "post" condition for all the scenarios and even steps.
 */
public class Hooks {
    //import the @Before coming from oi.cucumber.java
    @Before
    public void setupMethod(){
        System.out.println("--> @Before: running before each scenario");
    }
    /*
    @After will be executed automatically after every scenario in the project
     */
    @After
    public void teardownMethod(Scenario scenario) {

        if (scenario.isFailed()) {

            byte[] screenShot = ((TakesScreenshot) Driver.getDriver()).getScreenshotAs(OutputType.BYTES);
            scenario.attach(screenShot, "image/png", scenario.getName());

            System.out.println("--> @After: Running after each scenario");
           // Driver.closeDriver();
        }
        BrowserUtils.sleep(2);
        Driver.closeDriver();

    }
    @BeforeStep
    public void setupStep(){
      //  System.out.println("-----> @BeforeStep: Running before each step!");
    }
    @AfterStep
    public void teardownStep(){

        //System.out.println("-----> @BeforeStep: Running after each step!");
    }


}
