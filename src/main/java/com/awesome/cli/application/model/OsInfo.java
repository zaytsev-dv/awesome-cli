package com.awesome.cli.application.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class OsInfo {
    private final String osName;
    private final String osVersion;
    private final String osArch;
}
