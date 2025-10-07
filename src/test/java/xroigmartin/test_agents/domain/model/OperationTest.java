package xroigmartin.test_agents.domain.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class OperationTest {

    @Test
    void should_resolve_operation_when_value_is_lowercase() {
        // Given
        String rawValue = "addition";

        // When
        Operation operation = Operation.from(rawValue);

        // Then
        assertEquals(Operation.ADDITION, operation);
    }

    @Test
    void should_throw_exception_when_operation_is_blank() {
        // Given
        String rawValue = "   ";

        // When / Then
        assertThrows(IllegalArgumentException.class, () -> Operation.from(rawValue));
    }

    @Test
    void should_throw_exception_when_operation_is_unknown() {
        // Given
        String rawValue = "EXPONENTIATION";

        // When / Then
        assertThrows(IllegalArgumentException.class, () -> Operation.from(rawValue));
    }
}
