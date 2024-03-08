package org.example.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

/**
 * Created by mertcaliskan
 * on 28/09/14.
 */
//@Configuration等价于<beans>
@Configuration
//@ComponentScan(basePackages = {"org.example"})等价于<context:component-scan base-package="org.example" />
@ComponentScan(basePackages = {"org.example"})
//@EnableWebMvc等价于<mvc:annotation-driven />
@EnableWebMvc
public class AppConfig {
}
