package xroigmartin.test_agents.application.port.in;

import java.math.BigDecimal;
import java.util.Objects;

import xroigmartin.test_agents.domain.model.Operation;

public record CalculateOperationCommand(Operation operation, BigDecimal firstOperand, BigDecimal secondOperand) {

    public CalculateOperationCommand {
        Objects.requireNonNull(operation, "operation must not be null");
        Objects.requireNonNull(firstOperand, "firstOperand must not be null");
        Objects.requireNonNull(secondOperand, "secondOperand must not be null");
    }
}
