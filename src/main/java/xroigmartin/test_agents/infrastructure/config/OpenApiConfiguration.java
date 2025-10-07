package xroigmartin.test_agents.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.servers.Server;

/**
 * Configures OpenAPI metadata used by Swagger UI.
 */
@Configuration
public class OpenApiConfiguration {

    @Bean
    public OpenAPI calculatorOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Calculator API")
                        .description("REST API that performs basic arithmetic operations.")
                        .version("1.0.0")
                        .license(new License().name("Apache 2.0").url("https://www.apache.org/licenses/LICENSE-2.0.html")))
                .externalDocs(new ExternalDocumentation()
                        .description("Project README")
                        .url("https://github.com/xroigmartin/test-agents"))
                .addServersItem(new Server().url("http://localhost:8080").description("Local environment"));
    }
}
