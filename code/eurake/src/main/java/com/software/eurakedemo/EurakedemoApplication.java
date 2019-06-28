package com.software.eurakedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurakedemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurakedemoApplication.class, args);
    }

}
