package xroigmartin.test_agents.domain.model;

import java.math.BigDecimal;
import java.util.Objects;

public record CalculationResult(Operation operation, BigDecimal value) {

    public CalculationResult {
        Objects.requireNonNull(operation, "operation must not be null");
        Objects.requireNonNull(value, "value must not be null");
    }
}
