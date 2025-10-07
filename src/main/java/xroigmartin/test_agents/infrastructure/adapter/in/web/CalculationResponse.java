package xroigmartin.test_agents.infrastructure.adapter.in.web;

import java.math.BigDecimal;
import java.util.Objects;

public record CalculationResponse(String operation, BigDecimal result) {

    public CalculationResponse {
        Objects.requireNonNull(operation, "operation must not be null");
        Objects.requireNonNull(result, "result must not be null");
    }
}
