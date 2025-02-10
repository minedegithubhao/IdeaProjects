package org.example;

import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BigEventSpringbootApplication {

    private static Logger logger = LoggerFactory.getLogger(BigEventSpringbootApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(BigEventSpringbootApplication.class, args);
        logger.trace("trace logging");
        logger.debug("debug logging");
        logger.info("info logging");
        logger.warn("warn logging");
        logger.error("error logging");
    }

}
