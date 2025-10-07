package xroigmartin.test_agents.application.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

import xroigmartin.test_agents.application.port.in.CalculateOperationCommand;
import xroigmartin.test_agents.application.port.in.CalculateOperationUseCase;
import xroigmartin.test_agents.domain.exception.DivisionByZeroException;
import xroigmartin.test_agents.domain.model.CalculationResult;
import xroigmartin.test_agents.domain.model.Operation;
import xroigmartin.test_agents.domain.service.BasicCalculator;
import xroigmartin.test_agents.domain.service.Calculator;

class DefaultCalculateOperationUseCaseTest {

    private final Calculator calculator = new BasicCalculator();
    private final CalculateOperationUseCase useCase = new DefaultCalculateOperationUseCase(calculator);

    @Test
    void should_return_sum_when_operation_is_addition() {
        // Given
        CalculateOperationCommand command = new CalculateOperationCommand(Operation.ADDITION, new BigDecimal("5"), new BigDecimal("3"));

        // When
        CalculationResult result = useCase.calculate(command);

        // Then
        assertEquals(Operation.ADDITION, result.operation());
        assertEquals(new BigDecimal("8"), result.value());
    }

    @Test
    void should_return_difference_when_operation_is_subtraction() {
        // Given
        CalculateOperationCommand command = new CalculateOperationCommand(Operation.SUBTRACTION, new BigDecimal("9"), new BigDecimal("4"));

        // When
        CalculationResult result = useCase.calculate(command);

        // Then
        assertEquals(Operation.SUBTRACTION, result.operation());
        assertEquals(new BigDecimal("5"), result.value());
    }

    @Test
    void should_return_product_when_operation_is_multiplication() {
        // Given
        CalculateOperationCommand command = new CalculateOperationCommand(Operation.MULTIPLICATION, new BigDecimal("7"), new BigDecimal("6"));

        // When
        CalculationResult result = useCase.calculate(command);

        // Then
        assertEquals(Operation.MULTIPLICATION, result.operation());
        assertEquals(new BigDecimal("42"), result.value());
    }

    @Test
    void should_return_quotient_when_operation_is_division() {
        // Given
        CalculateOperationCommand command = new CalculateOperationCommand(Operation.DIVISION, new BigDecimal("18"), new BigDecimal("3"));

        // When
        CalculationResult result = useCase.calculate(command);

        // Then
        assertEquals(Operation.DIVISION, result.operation());
        assertEquals(new BigDecimal("6"), result.value());
    }

    @Test
    void should_round_division_result_using_decimal128_precision() {
        // Given
        CalculateOperationCommand command = new CalculateOperationCommand(Operation.DIVISION, new BigDecimal("1"), new BigDecimal("3"));

        // When
        CalculationResult result = useCase.calculate(command);

        // Then
        assertEquals(Operation.DIVISION, result.operation());
        assertEquals(new BigDecimal("0.3333333333333333333333333333333333"), result.value());
    }

    @Test
    void should_return_power_when_operation_is_power() {
        // Given
        CalculateOperationCommand command = new CalculateOperationCommand(Operation.POWER, new BigDecimal("2"), new BigDecimal("3"));

        // When
        CalculationResult result = useCase.calculate(command);

        // Then
        assertEquals(Operation.POWER, result.operation());
        assertEquals(new BigDecimal("8"), result.value());
    }

    @Test
    void should_handle_negative_exponent_when_operation_is_power() {
        // Given
        CalculateOperationCommand command = new CalculateOperationCommand(Operation.POWER, new BigDecimal("4"), new BigDecimal("-2"));

        // When
        CalculationResult result = useCase.calculate(command);

        // Then
        assertEquals(Operation.POWER, result.operation());
        assertEquals(new BigDecimal("0.0625"), result.value());
    }

    @Test
    void should_return_percentage_when_operation_is_percentage() {
        // Given
        CalculateOperationCommand command = new CalculateOperationCommand(Operation.PERCENTAGE, new BigDecimal("200"), new BigDecimal("15"));

        // When
        CalculationResult result = useCase.calculate(command);

        // Then
        assertEquals(Operation.PERCENTAGE, result.operation());
        assertEquals(new BigDecimal("30"), result.value());
    }

    @Test
    void should_throw_division_by_zero_when_divisor_is_zero() {
        // Given
        CalculateOperationCommand command = new CalculateOperationCommand(Operation.DIVISION, new BigDecimal("5"), BigDecimal.ZERO);

        // When / Then
        assertThrows(DivisionByZeroException.class, () -> useCase.calculate(command));
    }
}
