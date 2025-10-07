package xroigmartin.test_agents.infrastructure.adapter.in.web;

import java.util.Objects;

/**
 * HTTP error payload sent whenever a request cannot be processed.
 *
 * @param message human-readable description of the problem
 */
public record ErrorResponse(String message) {

    public ErrorResponse {
        Objects.requireNonNull(message, "message must not be null");
    }
}
