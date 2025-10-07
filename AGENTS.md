# AGENTS.md — Guía para agentes (Java + Spring Boot, Maven, JUnit 5, Mockito)

## 0. Objetivo del proyecto
Servicio backend en Java con Spring Boot, diseñado con **arquitectura hexagonal**, principios **DDD** y **SOLID**. El código debe desarrollarse con **TDD** (primero tests unitarios) y estilo **BDD** (narrativa Given–When–Then dentro de los tests). El repositorio está alojado en **GitHub**.

## 1. Alcance del agente
**Puede**: correcciones de bugs, refactors pequeños, creación de puertos/adapters, casos de uso, entidades y tests unitarios; mejoras de documentación técnica y ejemplos.
**NO puede**: cambios de arquitectura global, operaciones destructivas del sistema, introducir dependencias no aprobadas, integración con servicios externos ni ejecutar comandos fuera de la lista blanca.

## 2. Entorno y setup
- **JDK**: 21 (o la versión fijada en `pom.xml`).
- **Build**: Maven (wrapper recomendado `./mvnw`).
- **Framework**: Spring Boot.
- **Tests**: JUnit 5 + Mockito (enfoque unitario).
- **Variables de entorno (dummy para pruebas)**: Si son necesarias, declarar valores seguros y no reales en `application-test.yml` o `application.properties` bajo el `src/test/resources`.
- **Instalación**: no requiere pasos especiales más allá de Maven y JDK.

## 3. Arquitectura (Hexagonal + DDD + SOLID)
### 3.1 Capas / anillos
- **domain/** (núcleo): entidades, value objects, agregados, reglas de negocio puras. Sin dependencias a frameworks.
- **application/** (casos de uso): orquesta lógica de aplicación, define **ports** (interfaces) que el dominio necesita.
- **infrastructure/** (adapters): implementaciones de los **ports** (por ejemplo, repositorios JPA, clientes HTTP), configuración de Spring, mapeos y persistencia.
- **bootstrap/** (opcional): arranque de la app (main) y wiring.

### 3.2 Puertos y adaptadores
- **Ports (interfaces) en application**: p.ej., `LoadOrderPort`, `SaveOrderPort`, `NotifyCustomerPort`.
- **Adapters (infrastructure)**: `JpaOrderRepositoryAdapter`, `RestNotificationAdapter`.
- Depender **solo** de puertos en `domain/application`. Los adapters dependen de frameworks.

### 3.3 Reglas DDD
- Mantener **bounded contexts** claros si aplica (separar módulos o paquetes).
- Agregados con invariantes; exponer operaciones expresivas.
- Ubiquitous language en nombres de paquetes, clases y métodos.

### 3.4 Principios SOLID
- SRP: clases con una responsabilidad.
- OCP: abierto a extensión, cerrado a modificación (usa interfaces/estrategias).
- LSP: evita sustituibilidad rota (no relajes contratos).
- ISP: interfaces pequeñas por caso de uso.
- DIP: depende de abstracciones (ports), no de concreciones.

## 4. Testing (TDD + BDD con JUnit 5 y Mockito)
- **Estrategia**: escribir primero el test que falla (Red), implementar mínimo para pasar (Green), refactor con seguridad (Refactor).
- **Estilo BDD**: en cada test, usar narrativa en nombres y secciones comentadas:
  - `// Given` (estado/fixtures), `// When` (acción), `// Then` (aserciones).
- **Scope**: tests unitarios (sin levantar contexto completo de Spring). Usar Mockito para dobles (mocks/stubs).
- **Convenciones**:
  - Nombres de test: `should_<comportamiento>_when_<condicion>()`.
  - Un test por comportamiento; mantener independiente y determinista.
  - Evitar IO real, relojes reales; inyectar dependencias/tiempo cuando sea necesario.
- **Ubicación**: `src/test/java/...` reflejando el mismo paquete del código probado.

## 5. Validación y criterios de aceptación
- **Criterios de éxito mínimos**:
  - `mvn test` en **verde**.
  - Cobertura orientativa: objetivo >= 80% en capas **domain** y **application** (si hay plugin de cobertura).
  - Sin warnings críticos del compilador.
- **Salida del agente**: plan breve, diffs mínimos, resultados de tests, riesgos, próximos pasos.

## 6. Estructura de paquetes (sugerida)
```
src/
  main/java/com/acme/<bc>/
    domain/
      model/         # entidades, VOs, agregados
      service/       # políticas/domain services (si aplican)
    application/
      port/in/       # puertos de entrada (casos de uso) - opcional
      port/out/      # puertos de salida (infra)
      usecase/       # casos de uso (aplicación)
    infrastructure/
      adapter/in/    # REST/CLI/etc (entradas)
      adapter/out/   # JPA/REST/FS/etc (salidas)
      config/        # configuración Spring
  test/java/com/acme/<bc>/
    ...              # tests unitarios JUnit5 + Mockito
```

## 7. Lista de comandos **seguros** (whitelist)
> Los agentes **solo** pueden ejecutar estos comandos. Sirven para compilar y correr tests de forma **controlada** sin realizar operaciones peligrosas (instalaciones externas, red, borrados, etc.). Ajusta `./mvnw` a `mvn` si no usas wrapper.

```bash
# Compilación y limpieza local
./mvnw -q clean
./mvnw -q -DskipITs -DskipTests=false test
./mvnw -q -DskipITs -DskipTests=false -DfailIfNoTests=false verify
# (Opcional) Empaquetado si el proyecto lo requiere
./mvnw -q -DskipITs -DskipTests=false package

# Formato/estilo (solo si el plugin está presente en el pom)
./mvnw -q spotless:apply      # opcional
./mvnw -q spotless:check      # opcional
./mvnw -q checkstyle:check    # opcional
./mvnw -q pmd:check           # opcional
./mvnw -q jacoco:report       # opcional (cobertura)
```

**Notas importantes sobre la lista blanca**:
- **Prohibido** ejecutar comandos fuera de esta lista, usar `curl`, `wget`, gestores de paquetes del SO, o scripts que accedan a red.
- No modificar globalmente el entorno. Nada de `rm -rf` ni comandos del sistema.
- Si un plugin opcional no existe, el agente **no** debe invocarlo.
- Para máxima portabilidad, preferir `./mvnw` (wrapper).

## 8. Convenciones de código
- Java 21, anotaciones de nullidad donde aplique.
- Clases del dominio **sin** anotaciones de Spring.
- Inyección por constructor en application/infrastructure.
- Evitar `static` compartido; preferir dependencias explícitas.
- Excepciones específicas del dominio; evitar genéricas.
- Nomenclatura: `XxxPort` (interfaces), `XxxAdapter` (implementaciones), `XxxUseCase` (aplicación).

## 9. Git y PRs (GitHub)
- Rama de trabajo: `agent/<issue-id>-<slug-corto>` (ej.: `agent/123-crear-pedido`).
- Convención de commits (Conventional Commits):
  - `feat: ...`, `fix: ...`, `refactor: ...`, `test: ...`, `docs: ...`
- Abrir PR cuando:
  - Tests en verde localmente.
  - Diff manejable (< ~400 LOC) y con descripción del cambio y riesgos.
- Checklist del PR (agente debe incluir en la descripción):
  - [ ] Resumen del cambio y motivación
  - [ ] Evidencia de TDD (tests añadidos/actualizados)
  - [ ] Impacto en domain/application/infrastructure
  - [ ] Riesgos y mitigaciones
  - [ ] TODOs/seguimiento

## 10. Flujo de trabajo del agente
1) **Leer** este `AGENTS.md`, `README.md`, `pom.xml` y el código relevante.
2) **Planificar** en 5-8 líneas (subtareas y criterios de aceptación).
3) **TDD**: escribir un test unitario que falle (Given–When–Then).
4) **Implementar** el mínimo para pasar el test (domain/application primero; adapters después si aplica).
5) **Refactor** respetando SOLID; mantener diffs mínimos.
6) **Validar** con comandos de la lista blanca.
7) **Entregar**: diffs, resultados de `mvn test`, riesgos y próximos pasos.

## 11. Plantillas rápidas (orientativas)

### 11.1 Puerto de salida (application/port/out)
```java
package com.acme.orders.application.port.out;

import java.util.Optional;
import com.acme.orders.domain.model.Order;

public interface LoadOrderPort {
    Optional<Order> byId(Order.Id id);
}
```

### 11.2 Caso de uso (application/usecase)
```java
package com.acme.orders.application.usecase;

import com.acme.orders.application.port.out.LoadOrderPort;
import com.acme.orders.domain.model.Order;

public final class GetOrderUseCase {
    private final LoadOrderPort loadOrderPort;

    public GetOrderUseCase(LoadOrderPort loadOrderPort) {
        this.loadOrderPort = loadOrderPort;
    }

    public Order execute(Order.Id id) {
        return loadOrderPort.byId(id)
            .orElseThrow(() -> new Order.NotFound(id));
    }
}
```

### 11.3 Test unitario (JUnit 5 + Mockito, estilo BDD)
```java
package com.acme.orders.application.usecase;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

import java.util.Optional;
import org.junit.jupiter.api.Test;
import com.acme.orders.application.port.out.LoadOrderPort;
import com.acme.orders.domain.model.Order;

class GetOrderUseCaseTest {

    @Test
    void should_return_order_when_it_exists() {
        // Given
        LoadOrderPort port = mock(LoadOrderPort.class);
        Order.Id id = new Order.Id("o-1");
        Order expected = new Order(id);
        when(port.byId(id)).thenReturn(Optional.of(expected));
        GetOrderUseCase useCase = new GetOrderUseCase(port);

        // When
        Order result = useCase.execute(id);

        // Then
        // (campo ficticio para ejemplo)
        assertThat(result).isEqualTo(expected);
        verify(port).byId(id);
    }

    @Test
    void should_throw_when_order_not_found() {
        // Given
        LoadOrderPort port = mock(LoadOrderPort.class);
        Order.Id id = new Order.Id("missing");
        when(port.byId(id)).thenReturn(Optional.empty());
        GetOrderUseCase useCase = new GetOrderUseCase(port);

        // When / Then
        assertThatThrownBy(() -> useCase.execute(id))
            .isInstanceOf(Order.NotFound.class);
    }
}
```

## 12. Ejemplos de tareas para el agente
- Añadir **value object** `Money` con validaciones (dominio) y sus tests.
- Crear **use case** `CreateOrderUseCase` con puerto `SaveOrderPort` y tests.
- Implementar **adapter** JPA `JpaOrderRepositoryAdapter` (si existe DB), con tests aislados usando dobles.
- Refactorizar `Order` para encapsular invariantes y eliminar setters mutables.

## 13. FAQs / Trampas
- Evita tests que dependan de contexto Spring completo; para unidad usa simples clases + Mockito.
- No acoples `domain` a `infrastructure` (ni imports desde infra).
- Si necesitas reloj actual, inyecta una abstracción (p. ej., `Clock`) para controlar el tiempo en tests.
- Si no existe un plugin invocado en la lista blanca, **no** lo ejecutes.
