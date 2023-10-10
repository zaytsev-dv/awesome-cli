package com.awesome.cli.commands;

import com.awesome.cli.application.cache.CacheKey;
import com.awesome.cli.application.cache.CacheStore;
import com.awesome.cli.application.model.enums.CloudPropConstants;
import com.awesome.cli.application.usecase.FillCloudPortPropUseCase;
import com.awesome.cli.application.util.CommandTab;
import com.awesome.cli.application.util.PromptColor;
import com.awesome.cli.application.util.ShellHelper;
import com.awesome.cli.commands.dto.CloudPortProps;
import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
@AllArgsConstructor
public class CloudSettingCommand {
    private final FillCloudPortPropUseCase fillCloudPortPropUseCase;
    private final CacheStore<CloudPortProps> cacheStore;

    @ShellMethod(value = "create bucket in cloud", key = "create-bucket")
    public String createBucket(
            @ShellOption(value = {"--n"}, help = "name for your bucket", valueProvider = CommandTab.class) String bucketName,
            @ShellOption(value = {"--p"}, help = "is public bucket", defaultValue = "false", valueProvider = CommandTab.class) String isPublic
    ) {
        String bucketProp = CloudPropConstants.BUCKET_NAME + bucketName;
        return ShellHelper.getColored("Dont impl yet", PromptColor.RED);
    }

    @ShellMethod(value = "setup cloud settings", key = "setup-cloud-settings")
    public String setupCloud(
            @ShellOption(value = {"--ak"}, help = "access-key for cloud account", valueProvider = CommandTab.class) String accessKey,
            @ShellOption(value = {"--sk"}, help = "secret-key for cloud account", valueProvider = CommandTab.class) String secretKey,
            @ShellOption(value = {"--h"}, help = "host for cloud account", valueProvider = CommandTab.class) String host,
            @ShellOption(value = {"--t"}, help = "cloud system",defaultValue = "yandex", valueProvider = CommandTab.class) String type
    ) {
        CloudPortProps props = CloudPortProps.builder()
                .cloudTypeProp(accessKey)
                .accessKeyProp(secretKey)
                .secretKeyProp(host)
                .hostProp(type)
                .build();
        cacheStore.add(CacheKey.CLOUD_PROPS, props);
        return ShellHelper.getColored("Dont impl yet", PromptColor.RED);
    }
}
