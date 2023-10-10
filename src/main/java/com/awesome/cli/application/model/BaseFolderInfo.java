package com.awesome.cli.application.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class BaseFolderInfo {
    private final String homeFolder;
    private final String rootCliFolder;
    private final String downloadFolder;
    private final String otherFolder;
}
