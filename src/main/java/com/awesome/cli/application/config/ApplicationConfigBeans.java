package com.awesome.cli.application.config;

import com.awesome.cli.application.cache.CacheStore;
import com.awesome.cli.application.model.BaseFolderInfo;
import com.awesome.cli.application.model.OsInfo;
import com.awesome.cli.application.util.ShellHelper;
import com.awesome.cli.commands.dto.CloudPortProps;
import org.jline.terminal.Terminal;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;

@Configuration
public class ApplicationConfigBeans {
    @Bean
    public ShellHelper shellHelper(@Lazy Terminal terminal) {
        return new ShellHelper(terminal);
    }

    @Bean
    public CacheStore<OsInfo> osInfoCacheStore() {
        return new CacheStore<>();
    }

    @Bean
    public CacheStore<BaseFolderInfo> baseInfoCacheStore() {
        return new CacheStore<>();
    }

    @Bean
    public CacheStore<CloudPortProps> cloudPortPropsCacheStore() {
        return new CacheStore<>();
    }
}
