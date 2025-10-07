package xroigmartin.test_agents.infrastructure.adapter.in.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xroigmartin.test_agents.application.port.in.CalculateOperationCommand;
import xroigmartin.test_agents.application.port.in.CalculateOperationUseCase;
import xroigmartin.test_agents.domain.model.CalculationResult;
import xroigmartin.test_agents.domain.model.Operation;

/**
 * HTTP adapter that exposes the calculator use case through a REST endpoint.
 */
@RestController
@RequestMapping("/api/v1/calculator")
public final class CalculatorController {

    private final CalculateOperationUseCase calculateOperationUseCase;

    /**
     * Creates the controller with the required use case.
     *
     * @param calculateOperationUseCase application service that executes calculator commands
     */
    public CalculatorController(CalculateOperationUseCase calculateOperationUseCase) {
        this.calculateOperationUseCase = calculateOperationUseCase;
    }

    /**
     * Resolves the requested operation and returns its result.
     *
     * @param request payload containing the operation and operands
     * @return response containing the operation name and result
     */
    @PostMapping
    public CalculationResponse calculate(@RequestBody CalculationRequest request) {
        Operation operation = Operation.from(request.operation());
        CalculateOperationCommand command = new CalculateOperationCommand(operation, request.firstOperand(), request.secondOperand());
        CalculationResult result = calculateOperationUseCase.calculate(command);
        return new CalculationResponse(result.operation().name(), result.value());
    }
}
