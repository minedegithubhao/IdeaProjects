package org.example.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by mertcaliskan
 * on 28/09/14.
 */
@Configuration
@ComponentScan(basePackages = {"org.example"})
@EnableWebMvc
public class AppConfig {
}
