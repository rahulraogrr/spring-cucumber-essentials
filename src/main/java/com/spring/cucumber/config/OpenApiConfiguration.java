package com.spring.cucumber.config;

import com.spring.cucumber.constants.SwaggerConstants;
import com.spring.cucumber.utils.properties.SwaggerProperties;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfiguration {

    @Autowired
    private SwaggerProperties swaggerProperties;

    @Bean
    public OpenAPI customOpenApi(){
        return new OpenAPI()
                .info(
                        new Info()
                                .title(swaggerProperties.getInfo().get(SwaggerConstants.INFO_TITLE))
                                .version(swaggerProperties.getInfo().get(SwaggerConstants.INFO_VERSION))
                                .description(swaggerProperties.getInfo().get(SwaggerConstants.INFO_DESCRIPTION))
                                .termsOfService(swaggerProperties.getInfo().get(SwaggerConstants.INFO_TERMS_OF_SERVICE))
                                .contact(
                                        new Contact()
                                                .name(swaggerProperties.getInfo().get(SwaggerConstants.INFO_CONTACT_NAME))
                                                .url(swaggerProperties.getInfo().get(SwaggerConstants.INFO_CONTACT_URL))
                                                .email(swaggerProperties.getInfo().get(SwaggerConstants.INFO_CONTACT_EMAIL))
                                )
                );
    }
}