package com.awesome.cli.application.model;

import com.awesome.cli.application.model.enums.OsInfoConstant;
import lombok.AllArgsConstructor;
import lombok.Data;

import static com.awesome.cli.application.usecase.impl.InitOsInfoUseCaseImpl.NEXT_LINE;

@AllArgsConstructor
@Data
public class OsInfo {
    private final String osName;
    private final String osVersion;
    private final String osArch;

    public String format() {
        return OsInfoConstant.NAME.getText() + ": " + this.getOsName() + NEXT_LINE +
                OsInfoConstant.VERSION.getText() + ": " + this.getOsVersion() + NEXT_LINE +
                OsInfoConstant.ARCH.getText() + ": " + this.getOsArch();
    }
}
