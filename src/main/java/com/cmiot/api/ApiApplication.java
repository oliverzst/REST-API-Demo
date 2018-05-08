package com.cmiot.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 启动类
 *
 * @author zhangst
 * @version 2018-5-3
 */
@SpringBootApplication(scanBasePackages = "com.cmiot.api")
public class ApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiApplication.class, args);
    }

}
