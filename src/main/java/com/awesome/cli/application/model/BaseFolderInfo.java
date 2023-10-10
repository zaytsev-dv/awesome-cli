package com.awesome.cli.application.model;

import com.awesome.cli.application.model.enums.BaseFolderInfoConstant;
import lombok.AllArgsConstructor;
import lombok.Data;

import static com.awesome.cli.application.usecase.impl.InitOsInfoUseCaseImpl.NEXT_LINE;

@AllArgsConstructor
@Data
public class BaseFolderInfo {
    private final String homeFolder;
    private final String rootCliFolder;
    private final String downloadFolder;
    private final String otherFolder;

    public String format() {
        return BaseFolderInfoConstant.HOME.getWithDelimiter() + homeFolder +
                NEXT_LINE +
                BaseFolderInfoConstant.ROOT_CLI.getWithDelimiter() + homeFolder +
                NEXT_LINE +
                BaseFolderInfoConstant.DOWNLOAD.getWithDelimiter() + downloadFolder +
                NEXT_LINE +
                BaseFolderInfoConstant.OTHER.getWithDelimiter() + otherFolder;
    }
}
