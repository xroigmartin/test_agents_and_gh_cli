package xroigmartin.test_agents.infrastructure.adapter.in.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import xroigmartin.test_agents.application.port.in.CalculateOperationCommand;
import xroigmartin.test_agents.application.port.in.CalculateOperationUseCase;
import xroigmartin.test_agents.domain.exception.DivisionByZeroException;
import xroigmartin.test_agents.domain.model.CalculationResult;
import xroigmartin.test_agents.domain.model.Operation;

class CalculatorControllerTest {

    private CalculateOperationUseCase useCase;
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        useCase = mock(CalculateOperationUseCase.class);
        CalculatorController controller = new CalculatorController(useCase);
        mockMvc = MockMvcBuilders.standaloneSetup(controller)
                .setControllerAdvice(new CalculatorControllerAdvice())
                .build();
    }

    @Test
    void should_return_operation_result_when_use_case_succeeds() throws Exception {
        // Given
        when(useCase.calculate(any())).thenReturn(new CalculationResult(Operation.ADDITION, new BigDecimal("8")));

        // When
        mockMvc.perform(post("/api/v1/calculator")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"operation\":\"ADDITION\",\"firstOperand\":5,\"secondOperand\":3}"))
                // Then
                .andExpect(status().isOk())
                .andExpect(content().json("{\"operation\":\"ADDITION\",\"result\":8}"));

        ArgumentCaptor<CalculateOperationCommand> captor = ArgumentCaptor.forClass(CalculateOperationCommand.class);
        verify(useCase).calculate(captor.capture());
        CalculateOperationCommand command = captor.getValue();
        assertEquals(Operation.ADDITION, command.operation());
        assertEquals(new BigDecimal("5"), command.firstOperand());
        assertEquals(new BigDecimal("3"), command.secondOperand());
    }

    @Test
    void should_return_bad_request_when_division_by_zero_happens() throws Exception {
        // Given
        when(useCase.calculate(any())).thenThrow(new DivisionByZeroException());

        // When / Then
        mockMvc.perform(post("/api/v1/calculator")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"operation\":\"DIVISION\",\"firstOperand\":5,\"secondOperand\":0}"))
                .andExpect(status().isBadRequest())
                .andExpect(content().json("{\"message\":\"Cannot divide by zero\"}"));
    }

    @Test
    void should_return_bad_request_when_operation_is_unknown() throws Exception {
        // When / Then
        mockMvc.perform(post("/api/v1/calculator")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"operation\":\"EXPONENTIATION\",\"firstOperand\":2,\"secondOperand\":3}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Unsupported operation: EXPONENTIATION"));
    }

    @Test
    void should_return_bad_request_when_request_is_missing_fields() throws Exception {
        // When / Then
        mockMvc.perform(post("/api/v1/calculator")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"operation\":\"ADDITION\",\"firstOperand\":2}"))
                .andExpect(status().isBadRequest());
    }

    @Test
    void should_return_bad_request_when_use_case_reports_illegal_argument() throws Exception {
        // Given
        when(useCase.calculate(any())).thenThrow(new IllegalArgumentException("Exponent must be an integer value"));

        // When / Then
        mockMvc.perform(post("/api/v1/calculator")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"operation\":\"POWER\",\"firstOperand\":2,\"secondOperand\":1.5}"))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("Exponent must be an integer value"));
    }
}
