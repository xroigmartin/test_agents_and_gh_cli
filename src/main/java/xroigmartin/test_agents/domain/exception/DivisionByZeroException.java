package xroigmartin.test_agents.domain.exception;

public final class DivisionByZeroException extends RuntimeException {

    public DivisionByZeroException() {
        super("Cannot divide by zero");
    }
}
