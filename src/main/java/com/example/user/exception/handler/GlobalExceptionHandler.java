package com.example.user.exception.handler;

import com.example.user.exception.EntityNotFoundException;
import com.example.user.model.BaseResponse;
import java.time.LocalDateTime;
import java.util.List;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Log4j2
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
  @ExceptionHandler(value = EntityNotFoundException.class)
  public ResponseEntity<BaseResponse<List<String>>> entityException(RuntimeException exception) {
    log.warn(exception);
    return createResponse(exception.getMessage(), HttpStatus.NOT_FOUND);
  }

  private ResponseEntity<BaseResponse<List<String>>> createResponse(
      String message,
      HttpStatus status
  ) {
    BaseResponse<List<String>> body = new BaseResponse<>();
    body.setTimestamp(LocalDateTime.now());
    body.setValue(List.of(message));
    return new ResponseEntity<>(body, status);
  }
}
