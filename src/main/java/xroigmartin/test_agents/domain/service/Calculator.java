package xroigmartin.test_agents.domain.service;

import java.math.BigDecimal;

public interface Calculator {

    BigDecimal add(BigDecimal augend, BigDecimal addend);

    BigDecimal subtract(BigDecimal minuend, BigDecimal subtrahend);

    BigDecimal multiply(BigDecimal multiplicand, BigDecimal multiplier);

    BigDecimal divide(BigDecimal dividend, BigDecimal divisor);
}
