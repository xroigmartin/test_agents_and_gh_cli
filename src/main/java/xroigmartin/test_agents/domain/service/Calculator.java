package xroigmartin.test_agents.domain.service;

import java.math.BigDecimal;

/**
 * Defines arithmetic operations that a calculator implementation must provide.
 */
public interface Calculator {

    /**
     * Adds the given operands.
     *
     * @param augend value to which {@code addend} is added
     * @param addend value added to {@code augend}
     * @return sum of the operands
     */
    BigDecimal add(BigDecimal augend, BigDecimal addend);

    /**
     * Subtracts the second operand from the first.
     *
     * @param minuend value from which {@code subtrahend} is subtracted
     * @param subtrahend value subtracted from {@code minuend}
     * @return difference of the operands
     */
    BigDecimal subtract(BigDecimal minuend, BigDecimal subtrahend);

    /**
     * Multiplies the operands.
     *
     * @param multiplicand first operand
     * @param multiplier second operand
     * @return product of the operands
     */
    BigDecimal multiply(BigDecimal multiplicand, BigDecimal multiplier);

    /**
     * Divides the first operand by the second.
     *
     * @param dividend value to divide
     * @param divisor value that divides {@code dividend}
     * @return quotient of the operands
     * @throws xroigmartin.test_agents.domain.exception.DivisionByZeroException if {@code divisor} is zero
     */
    BigDecimal divide(BigDecimal dividend, BigDecimal divisor);

    /**
     * Raises {@code base} to the power of {@code exponent}. The exponent must be an integer.
     *
     * @param base value to be raised
     * @param exponent integer exponent applied to the base
     * @return base raised to exponent
     * @throws IllegalArgumentException when the exponent is not an integer value
     * @throws xroigmartin.test_agents.domain.exception.DivisionByZeroException when computing with a zero base and a negative exponent
     */
    BigDecimal power(BigDecimal base, BigDecimal exponent);

    /**
     * Calculates the percentage represented by {@code percent} over {@code base}.
     * {@code percent} is treated as a value between -infinity and +infinity (e.g. 15 means 15%).
     *
     * @param base value to apply the percentage to
     * @param percent percentage value
     * @return percentage of the base
     */
    BigDecimal percentage(BigDecimal base, BigDecimal percent);
}
