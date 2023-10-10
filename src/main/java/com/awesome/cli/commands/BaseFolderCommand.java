package com.awesome.cli.commands;

import com.awesome.cli.application.cache.CacheKey;
import com.awesome.cli.application.cache.CacheStore;
import com.awesome.cli.application.model.BaseFolderInfo;
import com.awesome.cli.application.util.PromptColor;
import com.awesome.cli.application.util.ShellHelper;
import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@AllArgsConstructor
public class BaseFolderCommand {
    private final CacheStore<BaseFolderInfo> cacheStore;

    @ShellMethod(value = "Print your base folders", key = "base-folder-info")
    public String printBaseFoldersInfo() {
        return String.join("\n", ShellHelper.getColored(cacheStore.get(CacheKey.BASE_FOLDER).format(), PromptColor.CYAN));
    }
}
