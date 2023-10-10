package com.awesome.cli.application.model.enums;

public enum FolderConstant {
    PROPS("props", null),
    OTHER("other", null),
    DOWNLOAD("download", null),
    HOME("home", "user.home"),
    ROOT("root", "user.dir");

    private final String name;
    private final String systemPropName;

    FolderConstant(String name, String systemPropName) {
        this.name = name;
        this.systemPropName = systemPropName;
    }

    public String getName() {
        return name;
    }

    public String getSystemPropName() {
        return systemPropName;
    }

    public String getNameWithDelimiter() {
        return "/" + name;
    }
}
