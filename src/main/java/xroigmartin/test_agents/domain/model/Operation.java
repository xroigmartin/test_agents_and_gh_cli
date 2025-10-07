package xroigmartin.test_agents.domain.model;

import java.util.Locale;
import java.util.Objects;

/**
 * Enumerates the supported calculator operations.
 */
public enum Operation {
    ADDITION,
    SUBTRACTION,
    MULTIPLICATION,
    DIVISION,
    POWER,
    PERCENTAGE;

    /**
     * Resolves an {@link Operation} from a case-insensitive string representation.
     *
     * @param rawValue input string to parse
     * @return the matching {@link Operation}
     * @throws IllegalArgumentException when the input is blank or not a supported value
     */
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
