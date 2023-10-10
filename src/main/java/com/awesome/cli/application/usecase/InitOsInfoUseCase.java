package com.awesome.cli.application.usecase;

import com.awesome.cli.application.model.OsInfo;

public interface InitOsInfoUseCase {
    String getFormattedFromFileSystem();
    OsInfo getFromFileSystemInfo();
}
