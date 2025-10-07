package xroigmartin.test_agents.domain.service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.Objects;

import xroigmartin.test_agents.domain.exception.DivisionByZeroException;

/**
 * Default implementation of {@link Calculator} that relies on {@link MathContext#DECIMAL128}
 * to perform deterministic decimal arithmetic.
 */
public final class BasicCalculator implements Calculator {

    private static final MathContext DEFAULT_MATH_CONTEXT = MathContext.DECIMAL128;
    private static final BigDecimal ONE_HUNDRED = new BigDecimal("100");

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

    @Override
    public BigDecimal power(BigDecimal base, BigDecimal exponent) {
        BigDecimal safeBase = requireNonNull(base, "base");
        BigDecimal safeExponent = requireNonNull(exponent, "exponent");
        if (safeExponent.scale() > 0) {
            throw new IllegalArgumentException("Exponent must be an integer value");
        }
        int exponentValue;
        try {
            exponentValue = safeExponent.intValueExact();
        } catch (ArithmeticException exception) {
            throw new IllegalArgumentException("Exponent is outside the supported range", exception);
        }
        if (exponentValue >= 0) {
            return safeBase.pow(exponentValue, DEFAULT_MATH_CONTEXT);
        }
        if (safeBase.compareTo(BigDecimal.ZERO) == 0) {
            throw new DivisionByZeroException();
        }
        BigDecimal positivePower = safeBase.pow(Math.abs(exponentValue), DEFAULT_MATH_CONTEXT);
        return BigDecimal.ONE.divide(positivePower, DEFAULT_MATH_CONTEXT);
    }

    @Override
    public BigDecimal percentage(BigDecimal base, BigDecimal percent) {
        BigDecimal safeBase = requireNonNull(base, "base");
        BigDecimal safePercent = requireNonNull(percent, "percent");
        return safeBase.multiply(safePercent, DEFAULT_MATH_CONTEXT)
                .divide(ONE_HUNDRED, DEFAULT_MATH_CONTEXT);
    }

    private static BigDecimal requireNonNull(BigDecimal value, String name) {
        return Objects.requireNonNull(value, name + " must not be null");
    }
}
