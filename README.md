# Calculator API

## Overview
This project provides a simple REST API built with Spring Boot that emulates a calculator. It follows a hexagonal architecture to keep the domain logic isolated from infrastructure layers and exposes a single endpoint that handles the four basic arithmetic operations: addition, subtraction, multiplication and division.

## Running the tests
```bash
./mvnw -DskipITs -DskipTests=false test
```

## API documentation
Interactive documentation is served at [http://localhost:8080/swagger-ui/index.html](http://localhost:8080/swagger-ui/index.html) once the application is running. The generated OpenAPI definition can also be downloaded from [http://localhost:8080/v3/api-docs](http://localhost:8080/v3/api-docs) or inspected offline at `src/main/resources/openapi/calculator-api.yaml`.
