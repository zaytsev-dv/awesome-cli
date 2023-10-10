package com.awesome.cli.application.usecase.impl;

import com.awesome.cli.application.model.BaseFolderInfo;
import com.awesome.cli.application.model.enums.BaseFolderInfoConstant;
import com.awesome.cli.application.model.enums.FolderConstant;
import com.awesome.cli.application.usecase.InitFolderInfoUseCase;

import static com.awesome.cli.application.usecase.impl.InitOsInfoUseCaseImpl.NEXT_LINE;

public class InitFolderInfoUseCaseImpl implements InitFolderInfoUseCase {

    @Override
    public String getFormattedFromFileSystem() {
        BaseFolderInfo info = this.getFromFileSystemInfo();
        return BaseFolderInfoConstant.HOME.getText() + ": " + info.getHomeFolder() + NEXT_LINE +
                BaseFolderInfoConstant.ROOT_CLI.getText() + ": " + info.getRootCliFolder() + NEXT_LINE +
                BaseFolderInfoConstant.DOWNLOAD.getText() + ": " + info.getDownloadFolder() + NEXT_LINE +
                BaseFolderInfoConstant.OTHER.getText() + ": " + info.getOtherFolder();
    }

    @Override
    public BaseFolderInfo getFromFileSystemInfo() {
        String homeDir = System.getProperty(FolderConstant.HOME.getSystemPropName());
        String rootDir = System.getProperty(FolderConstant.ROOT.getSystemPropName());
        String downLoadDir = rootDir + FolderConstant.DOWNLOAD.getNameWithDelimiter();
        String otherDir = rootDir + FolderConstant.OTHER.getNameWithDelimiter();
        return new BaseFolderInfo(homeDir, rootDir, downLoadDir, otherDir);
    }
}
