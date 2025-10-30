package xroigmartin.test_agents.application.port.in;

import xroigmartin.test_agents.domain.model.CalculationResult;

/** Input port that evaluates arithmetic commands and returns their results. */
public interface CalculateOperationUseCase {

  /**
   * Executes the calculator command.
   *
   * @param command data describing the operation to perform
   * @return result of the calculation
   */
  CalculationResult calculate(CalculateOperationCommand command);
}
