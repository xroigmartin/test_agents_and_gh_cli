package xroigmartin.test_agents.infrastructure.adapter.in.web;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import xroigmartin.test_agents.domain.exception.DivisionByZeroException;

/** Maps domain and validation exceptions produced by the calculator to HTTP responses. */
@RestControllerAdvice(assignableTypes = CalculatorController.class)
public final class CalculatorControllerAdvice {

  /**
   * Translates {@link DivisionByZeroException} into a 400 response.
   *
   * @param exception thrown exception
   * @return error payload with the message
   */
  @ExceptionHandler(DivisionByZeroException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleDivisionByZero(DivisionByZeroException exception) {
    return new ErrorResponse(exception.getMessage());
  }

  /**
   * Handles malformed requests and unsupported operations.
   *
   * @param exception thrown exception
   * @return error payload with the message
   */
  @ExceptionHandler(IllegalArgumentException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleIllegalArgument(IllegalArgumentException exception) {
    return new ErrorResponse(exception.getMessage());
  }

  /**
   * Handles bean validation errors produced while binding request payloads.
   *
   * @param exception validation exception raised by Spring
   * @return error payload with the first validation error message
   */
  @ExceptionHandler(MethodArgumentNotValidException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleValidationErrors(MethodArgumentNotValidException exception) {
    String message =
        exception.getBindingResult().getAllErrors().stream()
            .findFirst()
            .map(DefaultMessageSourceResolvable::getDefaultMessage)
            .orElse("Validation failed");
    return new ErrorResponse(message);
  }
}
