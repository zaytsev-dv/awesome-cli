package com.awesome.cli.application.usecase.impl;

import com.awesome.cli.application.model.OsInfo;
import com.awesome.cli.application.model.enums.OsInfoConstant;
import com.awesome.cli.application.usecase.InitOsInfoUseCase;

import static com.awesome.cli.application.model.enums.OsInfoConstant.*;

public class InitOsInfoUseCaseImpl implements InitOsInfoUseCase {

    public static final String NEXT_LINE = "\n";

    @Override
    public String getFormattedFromFileSystem() {
        OsInfo info = this.getFromFileSystemInfo();
        return OsInfoConstant.NAME.getText() + ": " + info.getOsName() + NEXT_LINE +
                OsInfoConstant.VERSION.getText() + ": " + info.getOsVersion() + NEXT_LINE +
                OsInfoConstant.ARCH.getText() + ": " + info.getOsArch();
    }

    @Override
    public OsInfo getFromFileSystemInfo() {
        String osName = System.getProperty(NAME.getSystemPropName());
        String osVersion = System.getProperty(VERSION.getSystemPropName());
        String osArch = System.getProperty(ARCH.getSystemPropName());
        return new OsInfo(osName, osVersion, osArch);
    }
}
