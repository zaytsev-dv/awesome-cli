package com.awesome.cli.application.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class BaseInfo {
    private final String homeFolder;
    private final String rootCliFolder;
    private final String downloadFolder;
}
