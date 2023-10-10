package com.awesome.cli.application.model;

import com.awesome.cli.application.model.enums.CloudPropConstants;
import lombok.Builder;
import lombok.Data;

import static com.awesome.cli.application.usecase.impl.InitOsInfoUseCaseImpl.NEXT_LINE;

@Builder
@Data
public class CloudPortProps {
    private String accessKeyProp;
    private String secretKeyProp;
    private String hostProp;
    private String cloudTypeProp;

    public String format() {
        return CloudPropConstants.CLOUD_TYPE + ": " + this.cloudTypeProp + NEXT_LINE +
                CloudPropConstants.HOST + ": " + this.hostProp + NEXT_LINE +
                CloudPropConstants.ACCESS_KEY + ": " + this.accessKeyProp + NEXT_LINE +
                CloudPropConstants.SECRET_KEY + ": " + this.secretKeyProp;
    }
}
