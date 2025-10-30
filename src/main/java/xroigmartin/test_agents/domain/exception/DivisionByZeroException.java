package xroigmartin.test_agents.domain.exception;

/** Signals that a division attempted to use zero as the divisor. */
public final class DivisionByZeroException extends RuntimeException {

  public DivisionByZeroException() {
    super("Cannot divide by zero");
  }
}
