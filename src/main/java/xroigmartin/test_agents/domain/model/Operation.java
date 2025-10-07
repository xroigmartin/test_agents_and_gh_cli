package xroigmartin.test_agents.domain.model;

import java.util.Locale;
import java.util.Objects;

public enum Operation {
    ADDITION,
    SUBTRACTION,
    MULTIPLICATION,
    DIVISION;

    public static Operation from(String rawValue) {
        Objects.requireNonNull(rawValue, "operation must not be null");
        String normalized = rawValue.trim();
        if (normalized.isEmpty()) {
            throw new IllegalArgumentException("operation must not be blank");
        }
        try {
            return Operation.valueOf(normalized.toUpperCase(Locale.US));
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException("Unsupported operation: " + rawValue, exception);
        }
    }
}
