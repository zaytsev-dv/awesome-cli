package com.app.manager;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

@SpringBootApplication
@Slf4j
public class AppManagerApplication {

	public static void main(String[] args) {
		SpringApplication.run(AppManagerApplication.class, args);
	}
}
