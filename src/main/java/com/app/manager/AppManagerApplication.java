package com.app.manager;

import com.app.manager.cache.Cache;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.nio.file.Paths;

@SpringBootApplication
@Slf4j
@AllArgsConstructor
public class AppManagerApplication {

    private final Cache cache;

    public static void main(String[] args) {
        SpringApplication.run(AppManagerApplication.class, args);
    }

    @SneakyThrows
    @PostConstruct
    public void init() {
        String rootPath = Paths.get("").toAbsolutePath().toString();
        cache.checkDirectory(rootPath);
        cache.fillOrCreateSettings(rootPath);
        cache.fillOrCreateOsInfo(rootPath);
    }
}
