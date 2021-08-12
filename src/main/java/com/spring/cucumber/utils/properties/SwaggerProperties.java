package com.spring.cucumber.utils.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@Getter
@AllArgsConstructor
@ConfigurationProperties(prefix = "openapi")
public class SwaggerProperties {
    private final Map<String,String> info;
    private final Map<String,String> responseCode;
}
