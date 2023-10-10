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
public class StartCommand {
    private final ShellHelper shellHelper;

    @ShellMethod(value = "Set main folders", key = "start")
    public String setupMainProps(
            @ShellOption(value = {"--h"}, help = "Home folder. Uses for saving you props", valueProvider = CommandTab.class) String homeFolderName,
            @ShellOption(value = {"--d"}, help = "Download folder. Uses for downloading files from cloud", valueProvider = CommandTab.class) String downloadFolderName
    ) {
        return shellHelper.getColored("Dont impl yet", PromptColor.RED);
    }
}
