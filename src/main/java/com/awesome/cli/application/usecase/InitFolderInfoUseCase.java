package com.awesome.cli.application.usecase;

import com.awesome.cli.application.model.BaseInfo;

public interface InitFolderInfoUseCase {
    String getFormatted();

    BaseInfo getInfo();
}
