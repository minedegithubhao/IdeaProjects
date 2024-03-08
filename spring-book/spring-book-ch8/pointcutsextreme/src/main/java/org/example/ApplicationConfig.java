package org.example;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * User: mertcaliskan
 * Date: 30/06/14
 */
@Configuration
@ComponentScan(basePackages = {"org.example"})
@EnableAspectJAutoProxy
public class ApplicationConfig {
}
