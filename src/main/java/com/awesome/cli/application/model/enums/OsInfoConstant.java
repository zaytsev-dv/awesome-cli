package com.awesome.cli.application.model.enums;

public enum OsInfoConstant {
    NAME("OS Name", "os.name"),
    VERSION("OS Version", "os.version"),
    ARCH("OS Arch", "os.arch");

    private final String text;
    private final String systemPropName;

    OsInfoConstant(String text, String systemPropName) {
        this.text = text;
        this.systemPropName = systemPropName;
    }

    public String getText() {
        return text;
    }

    public String getSystemPropName() {
        return systemPropName;
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
