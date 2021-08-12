package com.spring.cucumber.utils.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@Getter @Setter
@ConfigurationProperties(prefix = "openapi")
public class SwaggerProperties {
    private Map<String,String> info;
    private Map<String,String> responseCode;
}
