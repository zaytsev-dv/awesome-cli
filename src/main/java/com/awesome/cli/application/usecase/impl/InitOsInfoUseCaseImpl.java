package com.awesome.cli.application.usecase.impl;

import com.awesome.cli.application.model.OsInfo;
import com.awesome.cli.application.usecase.InitOsInfoUseCase;

import static com.awesome.cli.application.model.enums.OsInfoConstant.*;

public class InitOsInfoUseCaseImpl implements InitOsInfoUseCase {

    public static final String DELIMITER = ":";
    public static final String NEXT_LINE = "\n";

    @Override
    public String getFormatted() {
        OsInfo info = this.getFromFileSystemInfo();
        return info.getOsName() +
                NEXT_LINE +
                info.getOsVersion() +
                NEXT_LINE +
                info.getOsArch();
    }

    @Override
    public OsInfo getFromFileSystemInfo() {
        String osName = NAME.getText() + DELIMITER + System.getProperty(NAME.getSystemPropName());
        String osVersion = VERSION.getText() + DELIMITER + System.getProperty(VERSION.getSystemPropName());
        String osArch = ARCH.getText() + DELIMITER + System.getProperty(ARCH.getSystemPropName());
        return new OsInfo(osName, osVersion, osArch);
    }
}
