package com.example.bookshop.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info =
        @io.swagger.v3.oas.annotations.info.Info(
            description = "API documentation for the Bookshop application",
            title = "Bookshop API",
            version = "1.0"),
    servers = {
      @Server(description = "Local ENV", url = "http://localhost:5008"),
    })
@SecurityScheme(
    name = "JWT Bearer Token",
    description = "JWT Authentication",
    type = SecuritySchemeType.HTTP,
    scheme = "bearer",
    in = SecuritySchemeIn.HEADER)
public class OpenApiConfig {

  @Bean
  public GroupedOpenApi publicApi() {
    return GroupedOpenApi.builder().group("user").pathsToMatch("/api/v1/user/**").build();
  }

  @Bean
  public GroupedOpenApi adminApi() {
    return GroupedOpenApi.builder().group("admin").pathsToMatch("/api/v1/admin/**").build();
  }

  @Bean
  public GroupedOpenApi publicApiV1() {
    return GroupedOpenApi.builder().group("public").pathsToMatch("/api/public/v1/**").build();
  }
}
