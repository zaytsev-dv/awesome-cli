package com.app.manager.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.function.BiConsumer;
import static com.app.manager.util.TextConstant.*;

public final class CustomFileUtils {

    public static final String CACHE_OSINFO_TXT = "/cache/osinfo.txt";
    public static final String CACHE_SETTINGS_TXT = "/cache/settings.txt";
    public static final String CACHE_DIR = "/cache";

    private CustomFileUtils() {
    }

    public static File saveNewFile(final String path) throws IOException {
        File savedFile = new File(path);
        savedFile.createNewFile();
        return savedFile;
    }

    public static void saveNewFileVoid(final String path) throws IOException {
        File savedFile = new File(path);
        savedFile.createNewFile();
    }

    public static void writeLineToFile(Path pathOsFile, String propName) throws IOException {
        Files.writeString(
                pathOsFile,
                propName + NEXT_LINE,
                StandardOpenOption.APPEND
        );
    }

    public static void readFileAndFillCache(final String path, final BiConsumer<String, String> biConsumer) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(path))) {
            for (String line; (line = br.readLine()) != null; ) {
                String[] parts = line.split(DELIMITER);
                String settingName = parts[0];
                String settingValue = parts[1];
                biConsumer.accept(settingName, settingValue);
            }
        }
    }
}
