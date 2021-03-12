package tech.przybysz.pms.locationsservice.web.rest.errors;

import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import tech.przybysz.pms.locationsservice.service.exception.DependencyEntityNotFoundException;
import tech.przybysz.pms.locationsservice.service.exception.EntityNotFoundException;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class ExceptionTranslator extends ResponseEntityExceptionHandler {

  @ExceptionHandler({EntityNotFoundException.class,
      tech.przybysz.pms.locationsservice.web.rest.util.EntityNotFoundException.class})
  protected ResponseEntity<Object> handleEntityNotFound(RuntimeException ex, WebRequest request) {
    Error apiError = new Error(NOT_FOUND);
    String message = "";
    if(ex instanceof tech.przybysz.pms.locationsservice.web.rest.util.EntityNotFoundException) {
      message = "NOT FOUND";
    } else {
      EntityNotFoundException exception = (EntityNotFoundException) ex;
      message = "[" + exception.getEntityName() + "]" + " not found.";
    }
    apiError.setMessage(message);
    return buildResponseEntity(apiError);
  }

  @ExceptionHandler(DependencyEntityNotFoundException.class)
  protected ResponseEntity<Object> handleDependencyEntityNotFoundException(DependencyEntityNotFoundException ex, WebRequest request) {
    Error apiError = new Error(BAD_REQUEST);
    apiError.setMessage("[" + ex.getEntityName() + "]" + "[" + ex.getDependencyName() + "]" + ex.getMessage());
    return buildResponseEntity(apiError);
  }

  private ResponseEntity<Object> buildResponseEntity(Error apiError) {
    apiError.setTimestamp(LocalDateTime.now());
    return new ResponseEntity<>(apiError, apiError.getStatus());
  }
}
