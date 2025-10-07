package xroigmartin.test_agents.domain.service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Objects;

import xroigmartin.test_agents.domain.exception.DivisionByZeroException;

public final class BasicCalculator implements Calculator {

    private static final MathContext DEFAULT_MATH_CONTEXT = MathContext.DECIMAL128;

    @Override
    public BigDecimal add(BigDecimal augend, BigDecimal addend) {
        return requireNonNull(augend, "augend").add(requireNonNull(addend, "addend"), DEFAULT_MATH_CONTEXT);
    }

    @Override
    public BigDecimal subtract(BigDecimal minuend, BigDecimal subtrahend) {
        return requireNonNull(minuend, "minuend").subtract(requireNonNull(subtrahend, "subtrahend"), DEFAULT_MATH_CONTEXT);
    }

    @Override
    public BigDecimal multiply(BigDecimal multiplicand, BigDecimal multiplier) {
        return requireNonNull(multiplicand, "multiplicand").multiply(requireNonNull(multiplier, "multiplier"), DEFAULT_MATH_CONTEXT);
    }

    @Override
    public BigDecimal divide(BigDecimal dividend, BigDecimal divisor) {
        BigDecimal safeDivisor = requireNonNull(divisor, "divisor");
        if (safeDivisor.compareTo(BigDecimal.ZERO) == 0) {
            throw new DivisionByZeroException();
        }
        return requireNonNull(dividend, "dividend").divide(safeDivisor, DEFAULT_MATH_CONTEXT);
    }

    private static BigDecimal requireNonNull(BigDecimal value, String name) {
        return Objects.requireNonNull(value, name + " must not be null");
    }
}
