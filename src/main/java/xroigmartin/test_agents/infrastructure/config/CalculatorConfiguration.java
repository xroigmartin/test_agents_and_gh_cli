package xroigmartin.test_agents.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import xroigmartin.test_agents.application.port.in.CalculateOperationUseCase;
import xroigmartin.test_agents.application.usecase.DefaultCalculateOperationUseCase;
import xroigmartin.test_agents.domain.service.BasicCalculator;
import xroigmartin.test_agents.domain.service.Calculator;

@Configuration
public class CalculatorConfiguration {

    @Bean
    public Calculator calculator() {
        return new BasicCalculator();
    }

    @Bean
    public CalculateOperationUseCase calculateOperationUseCase(Calculator calculator) {
        return new DefaultCalculateOperationUseCase(calculator);
    }
}
