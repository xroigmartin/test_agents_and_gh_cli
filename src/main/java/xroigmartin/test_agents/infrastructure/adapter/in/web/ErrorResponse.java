package xroigmartin.test_agents.infrastructure.adapter.in.web;

import java.util.Objects;

public record ErrorResponse(String message) {

    public ErrorResponse {
        Objects.requireNonNull(message, "message must not be null");
    }
}
