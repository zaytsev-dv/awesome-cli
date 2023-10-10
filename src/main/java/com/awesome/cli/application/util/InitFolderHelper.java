package com.awesome.cli.application.util;

import com.awesome.cli.application.model.BaseInfo;
import com.awesome.cli.application.model.OsInfo;
import com.awesome.cli.application.model.enums.FileConstant;
import com.awesome.cli.application.model.enums.FolderConstant;
import com.awesome.cli.application.usecase.InitFolderInfoUseCase;
import com.awesome.cli.application.usecase.InitOsInfoUseCase;
import com.awesome.cli.application.usecase.impl.InitFolderInfoUseCaseImpl;
import com.awesome.cli.application.usecase.impl.InitOsInfoUseCaseImpl;
import lombok.extern.slf4j.Slf4j;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import static com.awesome.cli.application.usecase.impl.InitOsInfoUseCaseImpl.NEXT_LINE;

@Slf4j
public class InitFolderHelper {

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
                    Files.writeString(filePath, info.getOsName() + NEXT_LINE, StandardOpenOption.APPEND);
                    Files.writeString(filePath, info.getOsVersion() + NEXT_LINE, StandardOpenOption.APPEND);
                    Files.writeString(filePath, info.getOsArch() + NEXT_LINE, StandardOpenOption.APPEND);
                } catch (Exception ex) {
                    log.error("{}", ex);
                }
            }

            case PROPS -> {
                try {
                    InitFolderInfoUseCase initFolderInfoUseCase = new InitFolderInfoUseCaseImpl();
                    BaseInfo baseInfo = initFolderInfoUseCase.getFromFileSystemInfo();
                    Path filePath = Paths.get(
                            this.getRootFolderString() +
                                    FolderConstant.PROPS.getNameWithDelimiter() +
                                    FileConstant.PROPS.getNameWithDelimiter()
                    );
                    Files.writeString(filePath, "HOME FOLDER: " + baseInfo.getHomeFolder() + NEXT_LINE, StandardOpenOption.APPEND);
                    Files.writeString(filePath, "ROOT CLI FOLDER: " + baseInfo.getRootCliFolder() + NEXT_LINE, StandardOpenOption.APPEND);
                    Files.writeString(filePath, "DOWNLOAD FOLDER: " + baseInfo.getRootCliFolder() + baseInfo.getDownloadFolder() + NEXT_LINE, StandardOpenOption.APPEND);
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
