package com.demo.cucumber;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com.demo",
        plugin = {"pretty", "html:target/cucumber.html",},
        tags = "not @ignore"
)
public class CucumberRunnerIT {
}
