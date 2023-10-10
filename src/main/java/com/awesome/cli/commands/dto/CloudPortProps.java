package com.awesome.cli.commands.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class CloudPortProps {
    private String accessKeyProp;
    private String secretKeyProp;
    private String hostProp;
    private String cloudTypeProp;
}
