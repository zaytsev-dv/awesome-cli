package com.app.manager.cache;

import com.app.manager.util.CustomFileUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static com.app.manager.util.TextConstant.*;


@Component
@Slf4j
public class Cache {
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

    @SneakyThrows
    public void addNewSettingToFile(
            final SettingConstant settingKey,
            final String settingValue
    ) {
        boolean settingConstantAlreadyExist = this.settings.get(settingKey) != null;
        String rootPath = getRootPath();
        Path filePath = Paths.get(rootPath);

        if (settingConstantAlreadyExist) {
            this.settings.get(settingKey).add(settingValue);
            List<String> lines = Files.readAllLines(filePath, StandardCharsets.UTF_8);

            try (BufferedReader br = new BufferedReader(new FileReader(rootPath))) {
                int lineNumber = 1;
                for (String line; (line = br.readLine()) != null; ) {
                    String[] parts = line.split(DELIMITER);

                    addSettingValueToAlreadyExistSettingKey(settingKey, settingValue, filePath, lines, lineNumber, parts);
                    lineNumber++;
                }
            }

        } else {
            ArrayList<String> values = new ArrayList<>();
            values.add(settingValue);
            this.settings.put(settingKey, values);

            addSettingValueToNewLine(settingKey, settingValue, filePath);
        }
    }

    private String getRootPath() {
        return this.settings.get(SettingConstant.PROJECT_ROOT).get(0) + CustomFileUtils.CACHE_SETTINGS_TXT;
    }

    private static void addSettingValueToNewLine(
            final SettingConstant settingKey,
            final String settingValue,
            final Path filePath
    ) throws IOException {
        CustomFileUtils.writeLineToFile(
                filePath,
                settingKey + DELIMITER + settingValue
        );
    }

    private static void addSettingValueToAlreadyExistSettingKey(
            final SettingConstant settingKey,
            final String settingValue,
            final Path filePath, List<String> lines,
            final int lineNumber,
            final String[] parts
    ) throws IOException {
        if (parts[0].equalsIgnoreCase(settingKey.toString())) {
            String newLine = settingKey + DELIMITER + parts[1] + DELIMITER_2 + settingValue;
            lines.set(lineNumber - 1, newLine);
            Files.write(filePath, lines, StandardCharsets.UTF_8);
        }
    }

    public HashMap<SettingConstant, List<String>> getAllSetting() {
        return this.settings;
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

    public List<String> getOsInfo() {
        return osInfo;
    }
}
