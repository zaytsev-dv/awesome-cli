package com.app.manager.cache;

import com.app.manager.util.CustomFileUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Component
@Slf4j
public class Cache {
    public static final String OS_NAME = "os.name";
    public static final String OS_VERSION = "os.version";
    public static final String OS_ARCH = "os.arch";
    public static final String DELIMITER = ":";
    public static final String NEXT_LINE = "\n";

    private final HashMap<SettingConstant, List<String>> settings = new HashMap<>();
    private final List<String> osInfo = new ArrayList<>();

    public void addNewSetting(
            final SettingConstant settingKey,
            final String settingValue
    ) {
        if (this.settings.get(settingKey) != null) {
            this.settings.get(settingKey).add(settingValue);
        } else {
            ArrayList<String> values = new ArrayList<>();
            values.add(settingValue);
            this.settings.put(settingKey, values);
        }
    }

    public HashMap<SettingConstant, List<String>> getAllSetting() {
        return this.settings;
    }

    public List<String> getSettingsByKey(
            final SettingConstant settingKey
    ) {
        return this.settings.get(settingKey);
    }

    public void fillOSInfo(final String infoValue) {
        osInfo.add(infoValue);
    }

    public String getFormattedOSInfo() {
        return String.join(NEXT_LINE, osInfo);
    }

    public void fillOrCreateOsInfo(String rootPath) throws IOException {
        String osinfoFilePath = rootPath + CustomFileUtils.CACHE_OSINFO_TXT;
        Path pathOsFile = Paths.get(osinfoFilePath);
        if (!Files.exists(pathOsFile)) {
            CustomFileUtils.saveNewFileVoid(osinfoFilePath);
            try {
                String osName = OsInfoConstant.NAME.getText() + DELIMITER + System.getProperty(OS_NAME);
                String osVersion = OsInfoConstant.VERSION.getText() + DELIMITER + System.getProperty(OS_VERSION);
                String osArch = OsInfoConstant.ARCH.getText() + DELIMITER + System.getProperty(OS_ARCH);

                CustomFileUtils.writeLineToFile(pathOsFile, osName);
                CustomFileUtils.writeLineToFile(pathOsFile, osVersion);
                CustomFileUtils.writeLineToFile(pathOsFile, osArch);

                fillOSInfo(osName);
                fillOSInfo(osVersion);
                this.fillOSInfo(osArch);
            } catch (IOException e) {
                log.error("{}", e.getMessage());
            }

        } else {
            CustomFileUtils.readFileAndFillCache(
                    osinfoFilePath,
                    (s, s2) -> fillOSInfo(OsInfoConstant.fromString(s).getText() + DELIMITER + s2)
            );
        }
    }

    public void fillOrCreateSettings(String rootPath) throws IOException {
        String settingsFilePath = rootPath + CustomFileUtils.CACHE_SETTINGS_TXT;
        if (!Files.exists(Paths.get(settingsFilePath))) {
            File savedFile = CustomFileUtils.saveNewFile(settingsFilePath);
            FileUtils.writeStringToFile(savedFile, SettingConstant.PROJECT_ROOT + DELIMITER + rootPath + NEXT_LINE, StandardCharsets.UTF_8);
            addNewSetting(SettingConstant.PROJECT_ROOT, rootPath);
        } else {
            CustomFileUtils.readFileAndFillCache(
                    settingsFilePath,
                    (s, s2) -> addNewSetting(SettingConstant.valueOf(s.toUpperCase()), s2)
            );
        }
    }

    public void checkDirectory(String rootPath) {
        if (!Files.exists(Paths.get(rootPath + CustomFileUtils.CACHE_DIR))) {
            new File(rootPath + CustomFileUtils.CACHE_DIR).mkdirs();
        }
    }
}
