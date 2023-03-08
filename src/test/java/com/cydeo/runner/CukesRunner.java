package com.cydeo.runner;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

@CucumberOptions(
        plugin = {
                "html:target/cucumber-reports.html",
        },
        features = "src/test/resources/features",  // content root path
        glue = "com/cydeo/step_definitions",  // source root path
        dryRun = false,
        tags = "",
        publish = true // generating a report with public link

)

public class CukesRunner {}


