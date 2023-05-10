package com.app.manager.commands;

import com.app.manager.cache.Cache;
import com.app.manager.cache.SettingConstant;
import com.app.manager.util.CommandTab;
import com.app.manager.util.ShellHelper;
import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
@AllArgsConstructor
public class Settings {

    private final ShellHelper shellHelper;
    private final Cache cache;

    @ShellMethod(value = "Set project root directory with project name", key = "set-project-dir-root")
    public String setProjectDir(
            @ShellOption(value = {"-n"}, valueProvider = CommandTab.class) String name,
            @ShellOption(value = {"-p"}, valueProvider = CommandTab.class) String path) {
        cache.addNewSetting(SettingConstant.PROJECT_DIR, path);
        return shellHelper.getSuccessMessage("New Setting Project dir is added" + "\n" +
                shellHelper.getWarningMessage("Project Name: " + name) + "\n" +
                shellHelper.getWarningMessage("Project Dir: " + path)
        );
    }


    @ShellMethod(value = "Print \"awesome-cli\" root directory", key = "print-awesome-cli-root-dir")
    public String printRootDir() {
        return shellHelper.getSuccessMessage(cache.getSettingsByKey(SettingConstant.PROJECT_ROOT).get(0));
    }
}
