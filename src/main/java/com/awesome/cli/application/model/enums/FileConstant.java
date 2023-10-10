package com.awesome.cli.application.model.enums;

public enum FileConstant {
    PROPS("BaseFolders.txt"),
    OS_INFO("OsInfo.txt");

    private final String name;

    FileConstant(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getNameWithDelimiter() {
        return "/" + name;
    }
}
