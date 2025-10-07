# Calculator API

## Overview
This project provides a simple REST API built with Spring Boot that emulates a calculator. It follows a hexagonal architecture to keep the domain logic isolated from infrastructure layers and exposes a single endpoint that handles the four basic arithmetic operations: addition, subtraction, multiplication and division.

## Running the tests
```bash
mvn -DskipITs -DskipTests=false test
```

## Usage
The API is available at `POST /api/v1/calculator`. Send a JSON payload containing the operation and the two operands:

```http
POST /api/v1/calculator
Content-Type: application/json

{
  "operation": "ADDITION",
  "firstOperand": 5,
  "secondOperand": 3
}
```

Successful responses return HTTP 200 with the executed operation and the result:

```json
{
  "operation": "ADDITION",
  "result": 8
}
```

Invalid requests (unsupported operation, blank fields, division by zero) return HTTP 400 with an error message:

```json
{
  "message": "Cannot divide by zero"
}
```

## OpenAPI definition
An OpenAPI specification describing the endpoint is available at `src/main/resources/openapi/calculator-api.yaml`. You can import it into tools such as Swagger UI or Postman to explore the contract interactively.
