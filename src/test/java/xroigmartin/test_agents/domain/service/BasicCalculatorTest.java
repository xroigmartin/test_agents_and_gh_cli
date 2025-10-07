package xroigmartin.test_agents.domain.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import xroigmartin.test_agents.domain.exception.DivisionByZeroException;

class BasicCalculatorTest {

    private final Calculator calculator = new BasicCalculator();

    @Test
    void should_return_sum_when_operands_include_negative_numbers() {
        // Given
        BigDecimal augend = new BigDecimal("-5.5");
        BigDecimal addend = new BigDecimal("2.2");

        // When
        BigDecimal result = calculator.add(augend, addend);

        // Then
        assertEquals(new BigDecimal("-3.3"), result);
    }

    @Test
    void should_return_zero_when_multiplying_by_zero() {
        // Given
        BigDecimal multiplicand = new BigDecimal("12345.6789");
        BigDecimal multiplier = BigDecimal.ZERO;

        // When
        BigDecimal result = calculator.multiply(multiplicand, multiplier);

        // Then
        assertEquals(0, result.compareTo(BigDecimal.ZERO));
    }

    @Test
    void should_return_decimal_result_when_dividing_non_terminating_fraction() {
        // Given
        BigDecimal dividend = BigDecimal.ONE;
        BigDecimal divisor = new BigDecimal("3");

        // When
        BigDecimal result = calculator.divide(dividend, divisor);

        // Then
        assertEquals(new BigDecimal("0.3333333333333333333333333333333333"), result);
    }

    @Test
    void should_throw_exception_when_dividing_by_zero() {
        // Given
        BigDecimal dividend = BigDecimal.TEN;
        BigDecimal divisor = BigDecimal.ZERO;

        // When / Then
        assertThrows(DivisionByZeroException.class, () -> calculator.divide(dividend, divisor));
    }

    @Test
    void should_throw_exception_when_augend_is_null() {
        // Given
        BigDecimal addend = BigDecimal.ONE;

        // When / Then
        assertThrows(NullPointerException.class, () -> calculator.add(null, addend));
    }
}
