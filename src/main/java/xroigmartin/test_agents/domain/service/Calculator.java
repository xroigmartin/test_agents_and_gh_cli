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
}
