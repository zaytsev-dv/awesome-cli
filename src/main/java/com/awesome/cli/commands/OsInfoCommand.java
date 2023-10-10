package com.awesome.cli.commands;

import com.awesome.cli.application.cache.CacheKey;
import com.awesome.cli.application.cache.CacheStore;
import com.awesome.cli.application.model.OsInfo;
import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@AllArgsConstructor
public class OsInfoCommand {
    private final CacheStore<OsInfo> cacheStore;

    @ShellMethod(value = "Print your computer info", key = "os-info")
    public String printOsInfo() {
        return String.join("\n", cacheStore.get(CacheKey.OS_INFO).format());
    }
}
