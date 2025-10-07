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

    @Test
    void should_compute_power_with_positive_exponent() {
        // Given
        BigDecimal base = new BigDecimal("2");
        BigDecimal exponent = new BigDecimal("5");

        // When
        BigDecimal result = calculator.power(base, exponent);

        // Then
        assertEquals(new BigDecimal("32"), result);
    }

    @Test
    void should_return_one_when_power_has_zero_exponent() {
        // Given
        BigDecimal base = new BigDecimal("7.3");
        BigDecimal exponent = BigDecimal.ZERO;

        // When
        BigDecimal result = calculator.power(base, exponent);

        // Then
        assertEquals(BigDecimal.ONE, result);
    }

    @Test
    void should_compute_power_with_negative_exponent() {
        // Given
        BigDecimal base = new BigDecimal("4");
        BigDecimal exponent = new BigDecimal("-2");

        // When
        BigDecimal result = calculator.power(base, exponent);

        // Then
        assertEquals(new BigDecimal("0.0625"), result);
    }

    @Test
    void should_throw_exception_when_exponent_is_not_integer() {
        // Given
        BigDecimal base = new BigDecimal("2");
        BigDecimal exponent = new BigDecimal("1.5");

        // When / Then
        assertThrows(IllegalArgumentException.class, () -> calculator.power(base, exponent));
    }

    @Test
    void should_throw_exception_when_zero_base_and_negative_exponent() {
        // Given
        BigDecimal base = BigDecimal.ZERO;
        BigDecimal exponent = new BigDecimal("-1");

        // When / Then
        assertThrows(DivisionByZeroException.class, () -> calculator.power(base, exponent));
    }

    @Test
    void should_compute_percentage_when_percent_is_positive() {
        // Given
        BigDecimal base = new BigDecimal("200");
        BigDecimal percent = new BigDecimal("15");

        // When
        BigDecimal result = calculator.percentage(base, percent);

        // Then
        assertEquals(new BigDecimal("30"), result);
    }

    @Test
    void should_compute_percentage_when_percent_is_negative() {
        // Given
        BigDecimal base = new BigDecimal("50");
        BigDecimal percent = new BigDecimal("-10");

        // When
        BigDecimal result = calculator.percentage(base, percent);

        // Then
        assertEquals(new BigDecimal("-5"), result);
    }
}
