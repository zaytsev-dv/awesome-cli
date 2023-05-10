package com.app.manager;

import com.app.manager.cache.Cache;
import com.app.manager.cache.SettingConstant;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
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

    @PostConstruct
    public void init(){
        cache.addNewSetting(SettingConstant.PROJECT_ROOT, Paths.get("").toAbsolutePath().toString());
        cache.fillOSInfo("OS Name: " +  System.getProperty("os.name"));
        cache.fillOSInfo("OS Version: " +  System.getProperty("os.version"));
        cache.fillOSInfo("OS Arch: " +  System.getProperty("os.arch"));
    }
}
