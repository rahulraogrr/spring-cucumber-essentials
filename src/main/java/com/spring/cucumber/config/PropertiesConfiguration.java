package com.spring.cucumber.config;

import com.spring.cucumber.utils.properties.MessageProperties;
import com.spring.cucumber.utils.properties.SwaggerProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;

@Configuration
@PropertySources(
        value = {
                @PropertySource(value = {"classpath:messages.properties"}),
                @PropertySource(value = {"classpath:swagger.properties"})
        }
)
@EnableConfigurationProperties(
        value = {
                MessageProperties.class,
                SwaggerProperties.class
        }
)
public class PropertiesConfiguration {

}