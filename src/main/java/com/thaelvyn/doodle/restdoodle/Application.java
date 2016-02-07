package com.thaelvyn.doodle.restdoodle;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author Julien Frisquet
 */
@EnableAutoConfiguration
@ComponentScan(basePackages = "com.thaelvyn.doodle.restdoodle")
public class Application {

    public static void main(final String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
