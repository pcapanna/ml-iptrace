package com.ml.iptrace;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class IpTraceApplication {

    public static void main(String[] args) {
        SpringApplication.run(IpTraceApplication.class, args);
    }

}
