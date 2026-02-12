package com.bradleycorro.tataskilltest2.shared.configuration.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuración centralizada de OpenAPI/Swagger para el microservicio.
 */
@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Tata Skills Test 2 - API",
                version = "1.0.0",
                description = "API del microservicio tata-skill-test2 (Cuentas y Movimientos)"
        )
)
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .externalDocs(new ExternalDocumentation()
                        .description("Documentacion externa")
                        .url("https://springdoc.org/"));
    }
}
