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
        ),
        servers = {
                @Server(url = "/api2/v1", description = "Base Path (context-path)")
        }
)
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info()
                        .title("Tata Skills Test 2 - API")
                        .version("1.0.0")
                        .description("API del microservicio tata-skill-test2 (Cuentas y Movimientos)")
                        .contact(new io.swagger.v3.oas.models.info.Contact()
                                .name("Bradley Corro")
                                .url("https://github.com/bradleycorro")
                                .email("noreply@example.com"))
                        .license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0"))
                )
                .externalDocs(new ExternalDocumentation()
                        .description("Documentacion externa")
                        .url("https://springdoc.org/"));
    }
}
