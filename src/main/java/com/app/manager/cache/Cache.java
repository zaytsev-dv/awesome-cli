package com.app.manager.cache;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
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
            ArrayList<String> values = new ArrayList<>();
            values.add(settingValue);
            this.settings.put(settingKey, values);
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
