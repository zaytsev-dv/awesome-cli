package com.awesome.cli.application.usecase.impl;

import com.awesome.cli.application.model.BaseInfo;
import com.awesome.cli.application.model.enums.FolderConstant;
import com.awesome.cli.application.usecase.InitFolderInfoUseCase;

import static com.awesome.cli.application.usecase.impl.InitOsInfoUseCaseImpl.NEXT_LINE;

public class InitFolderInfoUseCaseImpl implements InitFolderInfoUseCase {

    @Override
    public String getFormatted() {
        BaseInfo info = this.getInfo();
        return "HOME FOLDER: " + info.getHomeFolder() + NEXT_LINE +
                "ROOT CLI FOLDER: " +  info.getRootCliFolder() + NEXT_LINE +
                "DOWNLOAD FOLDER: " + info.getRootCliFolder() + info.getDownloadFolder();
    }

    @Override
    public BaseInfo getInfo() {
        String homeDir = System.getProperty(FolderConstant.HOME.getSystemPropName());
        String rootDir = System.getProperty(FolderConstant.ROOT.getSystemPropName());
        String downLoadDir = FolderConstant.DOWNLOAD.getNameWithDelimiter();
        return new BaseInfo(homeDir, rootDir, downLoadDir);
    }
}
