package com.bradleycorro.tataskillstest.shared.configuration.swagger;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;

import java.util.List;

/**
 * Configuración centralizada de OpenAPI/Swagger para el microservicio.
 * <p>
 * - Define título, descripción, versión y metadatos del API.
 * - Declara los servidores respetando el context-path configurado ("/api/v1").
 * - Estandariza tags y documentación externa para una experiencia profesional en Swagger UI.
 */
@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Tata Skills Test · Productos API",
                version = "${app.version:1.0.0}",
                description = "Microservicio de gestión de productos siguiendo arquitectura hexagonal. " +
                        "Incluye estandarización de respuestas (ApiResponse) y manejo global de errores.",
                contact = @Contact(name = "Bradley Fernando Corro Munoz"),
                license = @License(name = "Apache 2.0", url = "https://www.apache.org/licenses/LICENSE-2.0")
        ),
        servers = {
                @Server(url = "/api/v1", description = "Base Path (context-path)")
        },
        tags = {
                @Tag(name = "Productos", description = "Endpoints para la gestión de productos")
        }
)
public class OpenApiConfig {

    /**
     * Bean programático para enriquecer el modelo OpenAPI utilizado por Swagger UI.
     * Útil cuando se requiere personalización adicional más allá de las anotaciones.
     */
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new io.swagger.v3.oas.models.info.Info()
                        .title("Tata Skills Test · Productos API")
                        .version(System.getProperty("app.version", "1.0.0"))
                        .description("API del microservicio de Productos. Endpoints documentados con springdoc-openapi. "+
                                "Contratos estables mediante DTOs de entrada/salida y respuestas normalizadas.")
                        .contact(new io.swagger.v3.oas.models.info.Contact()
                                .name("Bradley Fernando Corro Munoz"))
                        .license(new io.swagger.v3.oas.models.info.License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0"))
                )
                .servers(List.of(
                        new io.swagger.v3.oas.models.servers.Server().url("/api/v1").description("Base Path (context-path)")
                ))
                .externalDocs(new ExternalDocumentation()
                        .description("Repositorio del proyecto")
                        .url(""));
    }
}
