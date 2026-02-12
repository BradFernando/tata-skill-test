# Microservicio de Gestión de Productos - Tata Skills Test

Este microservicio ha sido desarrollado como parte del proceso de selección para **Tata Consultancy Services (TCS)**. 

## Información del Candidato
- **Nombre:** Bradley Fernando Corro Munoz
- **Posición:** Desarrollador Backend Java
- **Objetivo:** Demostrar habilidades en el diseno de arquitecturas escalables, limpias y mantenibles utilizando Java, Spring Boot y Arquitectura Hexagonal.

## Propósito del Test
El propósito de este desarrollo es validar la capacidad técnica en:
1.  **Arquitectura Hexagonal:** Separación clara entre la lógica de negocio (Dominio), la orquestación (Aplicación) y las implementaciones técnicas (Infraestructura).
2.  **Flexibilidad de Persistencia:** Uso de Spring Data JPA para garantizar la compatibilidad con diferentes bases de datos relacionales (en este caso, configurado para **PostgreSQL**).
3.  **Estandarización de APIs:** Implementación de un formato de respuesta único (`ApiResponse<T>`) y el uso de DTOs específicos para entrada (`Request`) y salida (`Response`), asegurando que el modelo de dominio nunca se exponga directamente.
4.  **Calidad de Código:** Uso de patrones de diseno, mappers especializados y validaciones robustas.

## Estructura del Proyecto

El proyecto sigue el patrón de **Puertos y Adaptadores (Hexagonal)**:

### 1. Capa de Dominio (`domain`)
Contiene la lógica puramente de negocio, independiente de frameworks.
- **Models:** Entidades de negocio como `Producto`.
- **Ports In:** Interfaces que definen los casos de uso disponibles.
- **Ports Out:** Interfaces que definen los requerimientos de persistencia.
- **Exceptions:** Excepciones de negocio específicas.

### 2. Capa de Aplicación (`application`)
Orquesta el flujo de datos.
- **Use Cases:** Implementaciones de los puertos de entrada.
- **DTOs:** Objetos de transferencia de datos. Se separan en `ProductoRequest` (entrada) y `ProductoResponse` (salida) para desacoplar el contrato externo del dominio interno.

### 3. Capa de Infraestructura (`infrastructure`)
Implementaciones técnicas y detalles de frameworks.
- **Adapters In (Rest):** Controladores que exponen los endpoints.
- **Adapters Out (JPA):** Implementación de la persistencia usando Spring Data JPA y PostgreSQL.
- **Mappers:** Conversión entre entidades de base de datos y modelos de dominio.

### 4. Paquete Shared (`shared`)
Componentes transversales reutilizables.
- **ApiResponse:** Formato estándar de respuesta.
- **GlobalExceptionHandler:** Captura y transformación de errores a un formato estándar.

## Configuración

### Requisitos
- Java 21
- Maven 3.x
- PostgreSQL

### Ejecución
1. Configurar las credenciales de la base de datos en `src/main/resources/application.properties`.
2. Ejecutar con:
   ```bash
   mvn spring-boot:run
   ```

## Endpoints Principales
- `POST /api/v1/productos`: Crear un producto.
- `GET /api/v1/productos`: Listar todos los productos.
- `GET /api/v1/productos/{id}`: Obtener detalle de un producto.
- `PUT /api/v1/productos/{id}`: Actualizar un producto.
- `DELETE /api/v1/productos/{id}`: Eliminar un producto.

# Microservicio de Gestión de Productos - Tata Skills Test

Este microservicio ha sido desarrollado como parte del proceso de selección para **Tata Consultancy Services (TCS)**.

## Información del Candidato
- **Nombre:** Bradley Fernando Corro Munoz
- **Perfil:** Senior Backend Engineer (Java)
- **Objetivo:** Demostrar criterios senior en diseno, mantenibilidad, calidad y estandarización mediante Java 21, Spring Boot y Arquitectura Hexagonal.

## Propósito del Test
Validar competencias en:
1. **Arquitectura Hexagonal:** Separación estricta de Dominio (puro), Aplicación (orquestación) e Infraestructura (implementaciones técnicas).
2. **Persistencia flexible:** Spring Data JPA sobre **PostgreSQL** con mapeo explícito a modelos de dominio.
3. **Estandarización de API:** Respuesta única `ApiResponse<T>` y `GlobalExceptionHandler` transversal.
4. **Calidad y mantenibilidad:** Uso de mappers, DTOs validados, buenas prácticas HTTP y Java moderna (pattern matching, `Stream.toList()`).

## Stack Técnico
- Java 21, Spring Boot 4.x
- Spring Web, Spring Data JPA
- PostgreSQL (driver runtime)
- Validación: `spring-boot-starter-validation`
- Lombok
- Maven Wrapper

## Arquitectura (Hexagonal)
- **Dominio (`domain`):**
  - Model: `Producto` (POJO puro, sin dependencias de framework).
  - Ports In: `IProductoUseCaseIn` (qué puede hacer el dominio).
  - Ports Out: `IProductoRepositoryOut` (qué necesita el dominio del exterior).
  - Exceptions: `ProductoNotFoundException` (hereda de `BaseDomainException`).
- **Aplicación (`application`):**
  - Use Case: `ProductoUseCase` (valida, orquesta, aplica reglas de negocio, invoca puertos de salida).
  - DTOs: `ProductoRequest` (entrada validada desde REST).
- **Infraestructura (`infrastructure`):**
  - REST: `ProductoController` (adaptador de entrada, expone endpoints HTTP).
  - Persistencia JPA: `ProductoJpaAdapter`, `IProductoJpaRepository`, `ProductoEntity` (adaptador de salida).
  - Mapper: `ProductoMapper` (aisla dominio de JPA).
- **Shared (`shared`):**
  - `ApiResponse<T>`, `ErrorResponse` (formato único de respuesta).
  - `GlobalExceptionHandler` (traducción uniforme de errores).

### Decisiones de Diseno (criterio senior)
- Evitar fugas de infraestructura al dominio (entidades JPA NO se usan en el dominio).
- Desacoplamiento total de la API: El uso de `ProductoResponse` garantiza que cambios en el modelo de dominio no afecten obligatoriamente al contrato externo, y permite filtrar datos sensibles o calculados.
- Endpoints idempotentes, semántica de estados HTTP correcta (201, 200, 204, 404, 500).
- Listas inmutables vía `Stream.toList()` para prevenir mutaciones accidentales.
- `instanceof` con pattern matching para mayor legibilidad y seguridad.
- Mapper dedicado para preservar independencia del dominio y facilitar pruebas.

## Endpoints Principales
Base: `/api/v1/productos`
- `POST /` ? Crear producto (201)
- `GET /` ? Listar productos (200)
- `GET /{id}` ? Obtener por id (200/404)
- `PUT /{id}` ? Actualizar (200/404)
- `DELETE /{id}` ? Eliminar (204/404)

### Ejemplos (curl)
```bash
curl -X POST http://localhost:8080/api/v1/productos \
  -H "Content-Type: application/json" \
  -d '{"nombre":"Laptop","descripcion":"Ultrabook","precio":3500.00,"stock":10}'

curl http://localhost:8080/api/v1/productos
```

## Configuración y Ejecución
1. Configura PostgreSQL en `src/main/resources/application.properties`:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/tata
spring.datasource.username=postgres
spring.datasource.password=postgres
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
```
2. Compilar y ejecutar:
```bash
./mvnw clean compile -DskipTests
./mvnw spring-boot:run
```

## Calidad y Extensibilidad
- `GlobalExceptionHandler` garantiza respuestas homogéneas ante fallos controlados y no controlados.
- `ApiResponse<T>` unifica contrato de API y simplifica clientes.
- Separación por capas minimiza el costo de cambio (nueva BDD, nuevo canal de entrada, etc.).
- Cobertura futura: se sugiere incorporar pruebas unitarias para `ProductoUseCase` y pruebas slice para REST y JPA.

## Autor
- Bradley Fernando Corro Munoz — Enfocado en soluciones limpias, mantenibles y escalables bajo principios SOLID y arquitectura hexagonal.

