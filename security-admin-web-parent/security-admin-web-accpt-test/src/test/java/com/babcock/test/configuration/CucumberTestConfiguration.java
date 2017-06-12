package com.babcock.test.configuration;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(value = {"com.babcock.securityweb", "com.babcock.test"})
@PropertySource("test.properties")
@Profile("test")
public class CucumberTestConfiguration {

}