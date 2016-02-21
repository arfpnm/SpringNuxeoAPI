package com.nhs.trust.spring.test.cucumber.mock;
/**
 * @author arif.mohammed
 */
import org.junit.Ignore;
import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
@Ignore
@RunWith(Cucumber.class) 
@CucumberOptions(glue = { "com.nhs.trust.spring.test.cucumber.mock.steps" }, features = { "src/test/resources/scenarios/mock" })
public class NuxeoDocumentSearchCucumberMockTest {}
