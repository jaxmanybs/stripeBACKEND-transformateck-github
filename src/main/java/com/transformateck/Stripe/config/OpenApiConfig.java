package com.transformateck.Stripe.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    // https://github.com/swagger-api/swagger-ui#localization-and-translation
    // i10n (translations) is not implemented.

    @Value("${urlMain}")
    private String urlMain;

    @Bean
    public OpenAPI customOpenAPI() {

        Server serverLocal = new Server();
        serverLocal.setUrl(urlMain);
        serverLocal.setDescription("Default");

        Server serverLocal1 = new Server();
        serverLocal1.setUrl(urlMain);
        serverLocal1.setDescription("Local");

        final String securitySchemeName = "bearer-auth";

        return new OpenAPI()
                .addSecurityItem(new SecurityRequirement().addList(securitySchemeName))
                .components(new Components()
                        .addSecuritySchemes(securitySchemeName,
                                new SecurityScheme()
                                        .name(securitySchemeName)
                                        .type(SecurityScheme.Type.HTTP)
                                        .scheme("bearer")
                                        .bearerFormat("JWT")))
                .info(new Info().title("TRANSFORMATECK Documentación API").description(
                        "Documentación API de STRIPE desarrollo en Spring Boot 2 RESTful y OpenAPI 3."))
                //.addServersItem(serverLocal)
                .addServersItem(serverLocal1);
    }

}