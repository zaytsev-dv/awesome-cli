package com.awesome.cli.application.usecase.impl;

import com.awesome.cli.application.usecase.OsInfoUseCase;

import static com.awesome.cli.application.model.OsInfoConstant.*;

public class OsInfoUseCaseImpl implements OsInfoUseCase {

    public static final String OS_NAME = "os.name";
    public static final String OS_VERSION = "os.version";
    public static final String OS_ARCH = "os.arch";
    public static final String OS_HOME_FOLDER = "user.home";
    public static final String DELIMITER = ":";
    public static final String NEXT_LINE = "\n";

    @Override
    public String getFormatted() {
        String osName = NAME + DELIMITER + System.getProperty(OS_NAME);
        String osVersion = VERSION + DELIMITER + System.getProperty(OS_VERSION);
        String osArch = ARCH + DELIMITER + System.getProperty(OS_ARCH);
        String osHomeFolder = HOME + DELIMITER + System.getProperty(OS_HOME_FOLDER);
        return osName + NEXT_LINE + osVersion + NEXT_LINE + osArch + NEXT_LINE + osHomeFolder;
    }
}
