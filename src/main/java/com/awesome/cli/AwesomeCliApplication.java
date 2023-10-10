package com.awesome.cli;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@Slf4j
@AllArgsConstructor
public class AwesomeCliApplication {


    public static void main(String[] args) {
        SpringApplication.run(AwesomeCliApplication.class, args);
    }
}
