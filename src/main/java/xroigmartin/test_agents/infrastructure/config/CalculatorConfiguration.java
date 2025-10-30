package xroigmartin.test_agents.infrastructure.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import xroigmartin.test_agents.application.port.in.CalculateOperationUseCase;
import xroigmartin.test_agents.application.usecase.DefaultCalculateOperationUseCase;
import xroigmartin.test_agents.domain.service.BasicCalculator;
import xroigmartin.test_agents.domain.service.Calculator;

/** Spring configuration that wires the calculator domain services and application use cases. */
@Configuration
public class CalculatorConfiguration {

  /**
   * Provides the default calculator implementation.
   *
   * @return calculator bean
   */
  @Bean
  public Calculator calculator() {
    return new BasicCalculator();
  }

  /**
   * Exposes the calculator use case to infrastructure adapters.
   *
   * @param calculator domain calculator dependency
   * @return use case bean
   */
  @Bean
  public CalculateOperationUseCase calculateOperationUseCase(Calculator calculator) {
    return new DefaultCalculateOperationUseCase(calculator);
  }
}
