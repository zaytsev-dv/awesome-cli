package com.awesome.cli.commands;

import com.awesome.cli.application.util.CommandTab;
import com.awesome.cli.application.util.PromptColor;
import com.awesome.cli.application.util.ShellHelper;
import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
@AllArgsConstructor
public class CloudSettingCommand {

    @ShellMethod(value = "create bucket in cloud", key = "create-bucket")
    public String createBucket(
            @ShellOption(value = {"--n"}, help = "name for your bucket", valueProvider = CommandTab.class) String bucketName,
            @ShellOption(value = {"--p"}, help = "is public bucket", defaultValue = "false", valueProvider = CommandTab.class) String isPublic
    ) {
        return ShellHelper.getColored("Dont impl yet", PromptColor.RED);
    }

    @ShellMethod(value = "setup cloud settings", key = "setup-cloud-settings")
    public String setupCloud(
            @ShellOption(value = {"--ak"}, help = "access-key for cloud account", valueProvider = CommandTab.class) String accessKey,
            @ShellOption(value = {"--sk"}, help = "secret-key for cloud account", valueProvider = CommandTab.class) String secretKey,
            @ShellOption(value = {"--h"}, help = "host for cloud account", valueProvider = CommandTab.class) String host
    ) {
        return ShellHelper.getColored("Dont impl yet", PromptColor.RED);
    }
}
