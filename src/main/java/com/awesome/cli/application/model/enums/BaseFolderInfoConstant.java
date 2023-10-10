package com.awesome.cli.application.model.enums;

public enum BaseFolderInfoConstant {
    HOME("Home computer folder"),
    ROOT_CLI("Root cli folder"),
    DOWNLOAD("Download folder"),
    OTHER("Other folder");

    private final String text;

    BaseFolderInfoConstant(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public String getWithDelimiter() {
        return this + ": ";
    }
}
