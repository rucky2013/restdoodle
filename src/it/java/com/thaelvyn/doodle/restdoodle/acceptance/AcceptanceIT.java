package com.thaelvyn.doodle.restdoodle.acceptance;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

/**
 * @author Julien Frisquet
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty",
                "html:target/cucumber/acceptance/api",
                "junit:target/cucumber/acceptance/api.xml",
                "json:target/cucumber/acceptance/acceptance.json"},
        features = {
                "classpath:features/companies.feature"
        },
        strict = true
)
public class AcceptanceIT {
}
