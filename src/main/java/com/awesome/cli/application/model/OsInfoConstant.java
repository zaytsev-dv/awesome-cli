package com.awesome.cli.application.model;

public enum OsInfoConstant {
    NAME("OS Name"),
    VERSION("OS Version"),
    ARCH("OS Arch");

    private final String text;

    OsInfoConstant(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public static OsInfoConstant fromString(String text) {
        for (OsInfoConstant b : OsInfoConstant.values()) {
            if (b.text.equalsIgnoreCase(text)) {
                return b;
            }
        }
        throw new RuntimeException("OsInfoConstant not found");
    }
}
