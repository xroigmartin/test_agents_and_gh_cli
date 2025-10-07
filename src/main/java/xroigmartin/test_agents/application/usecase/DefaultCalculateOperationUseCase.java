package xroigmartin.test_agents.application.usecase;

import java.math.BigDecimal;
import java.util.Objects;

import xroigmartin.test_agents.application.port.in.CalculateOperationCommand;
import xroigmartin.test_agents.application.port.in.CalculateOperationUseCase;
import xroigmartin.test_agents.domain.model.CalculationResult;
import xroigmartin.test_agents.domain.model.Operation;
import xroigmartin.test_agents.domain.service.Calculator;

/**
 * Default implementation of {@link CalculateOperationUseCase} that delegates the actual mathematics to a domain {@link Calculator}.
 */
public final class DefaultCalculateOperationUseCase implements CalculateOperationUseCase {

    private final Calculator calculator;

    /**
     * Creates the use case with the calculator dependency.
     *
     * @param calculator domain service used to execute operations
     */
    public DefaultCalculateOperationUseCase(Calculator calculator) {
        this.calculator = Objects.requireNonNull(calculator, "calculator must not be null");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public CalculationResult calculate(CalculateOperationCommand command) {
        Objects.requireNonNull(command, "command must not be null");
        BigDecimal result = resolveCalculation(command.operation(), command.firstOperand(), command.secondOperand());
        return new CalculationResult(command.operation(), result);
    }

    private BigDecimal resolveCalculation(Operation operation, BigDecimal firstOperand, BigDecimal secondOperand) {
        return switch (operation) {
            case ADDITION -> calculator.add(firstOperand, secondOperand);
            case SUBTRACTION -> calculator.subtract(firstOperand, secondOperand);
            case MULTIPLICATION -> calculator.multiply(firstOperand, secondOperand);
            case DIVISION -> calculator.divide(firstOperand, secondOperand);
            case POWER -> calculator.power(firstOperand, secondOperand);
            case PERCENTAGE -> calculator.percentage(firstOperand, secondOperand);
        };
    }
}
