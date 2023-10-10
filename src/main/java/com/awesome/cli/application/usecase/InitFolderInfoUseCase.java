package com.awesome.cli.application.usecase;

import com.awesome.cli.application.model.BaseFolderInfo;

public interface InitFolderInfoUseCase {
    String getFormattedFromFileSystem();

    BaseFolderInfo getFromFileSystemInfo();
}
