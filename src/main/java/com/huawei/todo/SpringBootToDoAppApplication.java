package com.huawei.todo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude={SecurityAutoConfiguration.class})
public class SpringBootToDoAppApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootToDoAppApplication.class, args);
    }

}
