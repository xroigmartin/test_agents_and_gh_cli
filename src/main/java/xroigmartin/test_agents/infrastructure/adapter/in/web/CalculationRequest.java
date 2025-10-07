package xroigmartin.test_agents.infrastructure.adapter.in.web;

import java.math.BigDecimal;
import java.util.Objects;

public record CalculationRequest(
        String operation,
        BigDecimal firstOperand,
        BigDecimal secondOperand) {

    public CalculationRequest {
        Objects.requireNonNull(operation, "operation must not be null");
        if (operation.isBlank()) {
            throw new IllegalArgumentException("operation must not be blank");
        }
        operation = operation.trim();
        Objects.requireNonNull(firstOperand, "firstOperand must not be null");
        Objects.requireNonNull(secondOperand, "secondOperand must not be null");
    }
}
