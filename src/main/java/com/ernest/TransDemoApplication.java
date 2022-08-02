package com.ernest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author Ernest
 */
@SpringBootApplication
@MapperScan("com.ernest.mapper")
public class TransDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(TransDemoApplication.class, args);
    }

}
