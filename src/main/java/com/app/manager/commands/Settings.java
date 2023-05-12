package com.app.manager.commands;

import com.app.manager.cache.Cache;
import com.app.manager.cache.SettingConstant;
import com.app.manager.util.CommandTab;
import com.app.manager.util.ShellHelper;
import lombok.AllArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

import java.util.HashMap;
import java.util.List;

@ShellComponent
@AllArgsConstructor
public class Settings {

    public static final int SCIP_FIRST_14_CHARACTERS = 14;
    private final ShellHelper shellHelper;
    private final Cache cache;

    @ShellMethod(value = "Set project directory with project name", key = "set-project-dir")
    public String setProjectDir(
            @ShellOption(value = {"-n"}, valueProvider = CommandTab.class) String name,
            @ShellOption(value = {"-p"}, valueProvider = CommandTab.class) String path) {
        cache.addNewSettingToFile(SettingConstant.PROJECT_DIR, path);
        return shellHelper.getSuccessMessage("New Setting Project dir is added" + "\n" +
                shellHelper.getWarningMessage("Project Name: " + name) + "\n" +
                shellHelper.getWarningMessage("Project Dir: " + path)
        );
    }

    //TODO: поиграться с цветом
    @ShellMethod(value = "Print \"awesome-cli\" all settings", key = "print-awesome-cli-all-settings")
    public String printAllSettings() {
        HashMap<SettingConstant, List<String>> allSetting = cache.getAllSetting();
        StringBuilder sb = new StringBuilder();
        allSetting.forEach((key,value) -> {
            sb.append(key)
                    .append(":");
            sb.append(String.join("\n" + StringUtils.repeat(' ', SCIP_FIRST_14_CHARACTERS), value))
                    .append("\n");
        });
        return sb.toString();
    }

    @ShellMethod(value = "Print your computer info", key = "os-info")
    public String printOsInfo() {
        return String.join("\n", cache.getOsInfo());

    }
}
