package com.spring.cucumber.utils.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = "msg")
@Getter @AllArgsConstructor
public class MessageProperties {
    private final Map<String,String> internalServerError;
    private final Map<String,String> badGateway;
}