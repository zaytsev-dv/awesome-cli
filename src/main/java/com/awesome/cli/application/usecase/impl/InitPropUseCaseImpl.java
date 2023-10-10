package com.awesome.cli.application.usecase.impl;

import com.awesome.cli.application.model.enums.FileConstant;
import com.awesome.cli.application.model.enums.FolderConstant;
import com.awesome.cli.application.usecase.InitPropUseCase;
import com.awesome.cli.application.util.InitFolderHelper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@AllArgsConstructor
public class InitPropUseCaseImpl implements InitPropUseCase {
    private final InitFolderHelper initFolderHelper;

    @Override
    public void init() {
        this.initFolderHelper.createDirIfNotExist(FolderConstant.PROPS.getNameWithDelimiter());
        this.initFolderHelper.createDirIfNotExist(FolderConstant.DOWNLOAD.getNameWithDelimiter());
        this.initFolderHelper.createDirIfNotExist(FolderConstant.OTHER.getNameWithDelimiter());

        this.initFolderHelper.createFileIfNotExist(FolderConstant.PROPS.getNameWithDelimiter() + FileConstant.PROPS.getNameWithDelimiter());
        this.initFolderHelper.createFileIfNotExist(FolderConstant.OTHER.getNameWithDelimiter() + FileConstant.OS_INFO.getNameWithDelimiter());

        this.initFolderHelper.fillFile(FileConstant.OS_INFO);
        this.initFolderHelper.fillFile(FileConstant.PROPS);
    }
}
