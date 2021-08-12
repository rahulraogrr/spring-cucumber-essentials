package com.spring.cucumber.utils.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@ConfigurationProperties(prefix = "msg")
@Getter @Setter
public class MessageProperties {
    private Map<String,String> internalServerError;
    private Map<String,String> badGateway;
}