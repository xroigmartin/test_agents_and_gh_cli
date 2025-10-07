package xroigmartin.test_agents.infrastructure.adapter.in.web;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import xroigmartin.test_agents.application.port.in.CalculateOperationCommand;
import xroigmartin.test_agents.application.port.in.CalculateOperationUseCase;
import xroigmartin.test_agents.domain.model.CalculationResult;
import xroigmartin.test_agents.domain.model.Operation;

@RestController
@RequestMapping("/api/v1/calculator")
public final class CalculatorController {

    private final CalculateOperationUseCase calculateOperationUseCase;

    public CalculatorController(CalculateOperationUseCase calculateOperationUseCase) {
        this.calculateOperationUseCase = calculateOperationUseCase;
    }

    @PostMapping
    public CalculationResponse calculate(@RequestBody CalculationRequest request) {
        Operation operation = Operation.from(request.operation());
        CalculateOperationCommand command = new CalculateOperationCommand(operation, request.firstOperand(), request.secondOperand());
        CalculationResult result = calculateOperationUseCase.calculate(command);
        return new CalculationResponse(result.operation().name(), result.value());
    }
}
