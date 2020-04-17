package com.automation.tests.nurullah.runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        glue = "com/automation/tests/nurullah/step_definitions",
        dryRun = true
)
public class CucumberRunner {
}
