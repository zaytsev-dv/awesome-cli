package com.awesome.cli.application.util;

import com.awesome.cli.application.cache.CacheStore;
import com.awesome.cli.application.model.BaseFolderInfo;
import com.awesome.cli.application.model.OsInfo;
import com.awesome.cli.application.model.enums.BaseFolderInfoConstant;
import com.awesome.cli.application.model.enums.FileConstant;
import com.awesome.cli.application.model.enums.FolderConstant;
import com.awesome.cli.application.usecase.InitFolderInfoUseCase;
import com.awesome.cli.application.usecase.InitOsInfoUseCase;
import com.awesome.cli.application.usecase.impl.InitFolderInfoUseCaseImpl;
import com.awesome.cli.application.usecase.impl.InitOsInfoUseCaseImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import static com.awesome.cli.application.model.enums.OsInfoConstant.*;
import static com.awesome.cli.application.usecase.impl.InitOsInfoUseCaseImpl.NEXT_LINE;

@Slf4j
@AllArgsConstructor
public class InitFolderHelper {
    private final CacheStore<OsInfo> osInfoCacheStore;
    private final CacheStore<BaseFolderInfo> baseFolderInfoCacheStore;

    public void createFileIfNotExist(final String file) {
        String rootUrl = this.getRootFolderString();
        Path filePath = Paths.get(rootUrl + file);
        try {
            if (!Files.exists(filePath)) {
                Files.createFile(filePath);
            }
        } catch (Exception ex) {
            log.error("{}", ex);
        }
    }

    private String getRootFolderString() {
        return System.getProperty(FolderConstant.ROOT.getSystemPropName());
    }

    public void fillFile(final FileConstant fileConstant) {
        switch (fileConstant) {
            case OS_INFO -> {
                try {
                    InitOsInfoUseCase initOsInfoUseCase = new InitOsInfoUseCaseImpl();
                    OsInfo info = initOsInfoUseCase.getFromFileSystemInfo();
                    Path filePath = Paths.get(
                            this.getRootFolderString() +
                                    FolderConstant.OTHER.getNameWithDelimiter() +
                                    FileConstant.OS_INFO.getNameWithDelimiter()
                    );

                    Files.writeString(filePath, NAME.getText() + ": " + info.getOsName() + NEXT_LINE, StandardOpenOption.APPEND);
                    Files.writeString(filePath, VERSION.getText() + ": " + info.getOsVersion() + NEXT_LINE, StandardOpenOption.APPEND);
                    Files.writeString(filePath, ARCH.getText() + ": " + info.getOsArch() + NEXT_LINE, StandardOpenOption.APPEND);
                    this.osInfoCacheStore.add("OS_INFO", info);
                } catch (Exception ex) {
                    log.error("{}", ex);
                }
            }

            case PROPS -> {
                try {
                    InitFolderInfoUseCase initFolderInfoUseCase = new InitFolderInfoUseCaseImpl();
                    BaseFolderInfo baseFolderInfo = initFolderInfoUseCase.getFromFileSystemInfo();
                    Path filePath = Paths.get(
                            this.getRootFolderString() +
                                    FolderConstant.PROPS.getNameWithDelimiter() +
                                    FileConstant.PROPS.getNameWithDelimiter()
                    );
                    Files.writeString(filePath, BaseFolderInfoConstant.HOME + ": " + baseFolderInfo.getHomeFolder() + NEXT_LINE, StandardOpenOption.APPEND);
                    Files.writeString(filePath, BaseFolderInfoConstant.ROOT_CLI + ": " + baseFolderInfo.getRootCliFolder() + NEXT_LINE, StandardOpenOption.APPEND);
                    Files.writeString(filePath, BaseFolderInfoConstant.DOWNLOAD + ": " +  baseFolderInfo.getDownloadFolder() + NEXT_LINE, StandardOpenOption.APPEND);
                    Files.writeString(filePath, BaseFolderInfoConstant.OTHER + ": " + baseFolderInfo.getOtherFolder() + NEXT_LINE, StandardOpenOption.APPEND);

                    this.baseFolderInfoCacheStore.add("BASE_FOLDER", baseFolderInfo);
                } catch (Exception ex) {
                    log.error("{}", ex);
                }
            }
        }
    }

    public void createDirIfNotExist(final String dir) {
        String rootUrl = this.getRootFolderString();
        Path dirPath = Paths.get(rootUrl + dir);
        try {
            if (!Files.exists(dirPath)) {
                Files.createDirectories(dirPath);
            }
        } catch (Exception ex) {
            log.error("{}", ex);
        }
    }
}
