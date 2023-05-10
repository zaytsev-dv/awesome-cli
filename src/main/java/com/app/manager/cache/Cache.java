package com.app.manager.cache;

import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Component
public class Cache {
    private final HashMap<SettingConstant, List<String>> settings = new HashMap<>();

    public void addNewSetting(
            final SettingConstant settingKey,
            final String settingValue
    ) {
        if (this.settings.get(settingKey) != null) {
            this.settings.get(settingKey).add(settingValue);
        } else {
            this.settings.put(settingKey, Arrays.asList(settingValue));
        }
    }

    public HashMap<SettingConstant, List<String>> getAllSetting() {
        return this.settings;
    }

    public List<String> getSettingsByKey(
            final SettingConstant settingKey
    ) {
        return this.settings.get(settingKey);
    }
}
