package xroigmartin.test_agents.infrastructure.adapter.in.web;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.math.BigDecimal;

import org.junit.jupiter.api.Test;

class CalculationRequestTest {

    @Test
    void should_trim_operation_when_value_contains_extra_spaces() {
        // Given
        String rawOperation = "  ADDITION  ";

        // When
        CalculationRequest request = new CalculationRequest(rawOperation, BigDecimal.ONE, BigDecimal.ZERO);

        // Then
        assertEquals("ADDITION", request.operation());
    }

    @Test
    void should_throw_exception_when_operation_is_blank() {
        // Given
        String rawOperation = "   ";

        // When / Then
        assertThrows(IllegalArgumentException.class, () -> new CalculationRequest(rawOperation, BigDecimal.ONE, BigDecimal.ONE));
    }

    @Test
    void should_throw_exception_when_first_operand_is_null() {
        // Given

        // When / Then
        assertThrows(NullPointerException.class, () -> new CalculationRequest("ADDITION", null, BigDecimal.ONE));
    }

    @Test
    void should_throw_exception_when_second_operand_is_null() {
        // Given

        // When / Then
        assertThrows(NullPointerException.class, () -> new CalculationRequest("ADDITION", BigDecimal.ONE, null));
    }
}
