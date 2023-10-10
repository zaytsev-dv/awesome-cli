package com.awesome.cli.commands;

import com.awesome.cli.application.util.PromptColor;
import com.awesome.cli.application.util.ShellHelper;
import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import static com.awesome.cli.application.usecase.impl.InitOsInfoUseCaseImpl.NEXT_LINE;


@ShellComponent
@AllArgsConstructor
public class StartCommand {
    private final ShellHelper shellHelper;

    @SneakyThrows
    @ShellMethod(value = "Set main folders", key = "start")
    public String setupMainProps(
//            @ShellOption(value = {"--h"}, help = "Home folder. Uses for saving you props", valueProvider = CommandTab.class) String homeFolderName,
//            @ShellOption(value = {"--d"}, help = "Download folder. Uses for downloading files from cloud", valueProvider = CommandTab.class) String downloadFolderName
    ) {
        String rootFolder = System.getProperty("user.dir") + "/props";
        Path rootFolderPath = Paths.get(rootFolder);
        if (Files.exists(rootFolderPath)) {
            Path baseFoldersPath = Paths.get(rootFolder + "/BaseFolders.txt");
            String homeFolderUrl = "HOME_FOLDER: " + System.getProperty("user.home");
            String rootFolderUrl = "ROOT_FOLDER: " + System.getProperty("user.dir");

            Files.writeString(baseFoldersPath, homeFolderUrl + NEXT_LINE, StandardOpenOption.APPEND);
            Files.writeString(baseFoldersPath, rootFolderUrl + NEXT_LINE, StandardOpenOption.APPEND);
        } else {
            Files.createDirectories(rootFolderPath);
            Files.createFile(Paths.get(rootFolderPath + "/BaseFolders.txt"));
        }

        return shellHelper.getColored("Dont impl yet", PromptColor.RED);
    }
}
