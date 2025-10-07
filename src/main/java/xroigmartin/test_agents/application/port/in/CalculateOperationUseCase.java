package xroigmartin.test_agents.application.port.in;

import xroigmartin.test_agents.domain.model.CalculationResult;

public interface CalculateOperationUseCase {

    CalculationResult calculate(CalculateOperationCommand command);
}
