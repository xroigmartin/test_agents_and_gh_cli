package xroigmartin.test_agents.domain.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 * Immutable value object that stores the operation executed and its numeric result.
 *
 * @param operation the executed operation
 * @param value numeric outcome of the calculation
 */
public record CalculationResult(Operation operation, BigDecimal value) {

    public CalculationResult {
        Objects.requireNonNull(operation, "operation must not be null");
        Objects.requireNonNull(value, "value must not be null");
    }
}
