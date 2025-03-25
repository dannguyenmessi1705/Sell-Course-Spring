package com.learn.course.exception;

import com.learn.course.model.Status;
import com.learn.course.model.dto.response.GeneralResponse;
import com.learn.course.util.SubStringURIUtils;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.resource.NoResourceFoundException;

public class GlobalException extends ResponseEntityExceptionHandler {

  @Override
  protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(HttpRequestMethodNotSupportedException ex,
      HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    Status statusErr = Status.builder()
        .path(SubStringURIUtils.cutURI(request.getDescription(false)))
        .code(HttpStatus.METHOD_NOT_ALLOWED.value())
        .message(ex.getMessage())
        .timestamp(LocalDateTime.now().toString())
        .build();
    return new ResponseEntity<>(new GeneralResponse<>(statusErr, null), HttpStatus.METHOD_NOT_ALLOWED);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
      HttpHeaders headers, HttpStatusCode status, WebRequest request) {
    Status statusErr = Status.builder()
        .path(SubStringURIUtils.cutURI(request.getDescription(false)))
        .code(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value())
        .message(ex.getMessage())
        .timestamp(LocalDateTime.now().toString())
        .build();
    return new ResponseEntity<>(new GeneralResponse<>(statusErr, null), HttpStatus.UNSUPPORTED_MEDIA_TYPE);
  }

  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers,
      HttpStatusCode status, WebRequest request) {
    Map<String, String> validationErrors = new HashMap<>();
    List<ObjectError> validationErrorsList = ex.getBindingResult().getAllErrors();

    validationErrorsList.forEach(error -> {
      String fieldName = ((FieldError) error).getField();
      String errorMessage = error.getDefaultMessage();
      validationErrors.put(fieldName, errorMessage);
    });
    Status statusErr = Status.builder()
        .path(SubStringURIUtils.cutURI(request.getDescription(false)))
        .code(HttpStatus.BAD_REQUEST.value())
        .message(ex.getMessage())
        .timestamp(LocalDateTime.now().toString())
        .build();
    return new ResponseEntity<>(new GeneralResponse<>(statusErr, validationErrors), HttpStatus.BAD_REQUEST);
  }

  @Override
  protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers,
      HttpStatusCode status, WebRequest request) {
    Status statusErr = Status.builder()
        .path(SubStringURIUtils.cutURI(request.getDescription(false)))
        .code(HttpStatus.NOT_FOUND.value())
        .message(ex.getMessage())
        .timestamp(LocalDateTime.now().toString())
        .build();
    return new ResponseEntity<>(new GeneralResponse<>(statusErr, null), HttpStatus.NOT_FOUND);
  }

  @Override
  protected ResponseEntity<Object> handleNoResourceFoundException(NoResourceFoundException ex, HttpHeaders headers,
      HttpStatusCode status, WebRequest request) {
    Status statusErr = Status.builder()
        .path(SubStringURIUtils.cutURI(request.getDescription(false)))
        .code(HttpStatus.NOT_FOUND.value())
        .message(ex.getMessage())
        .timestamp(LocalDateTime.now().toString())
        .build();
    return new ResponseEntity<>(new GeneralResponse<>(statusErr, null), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(Exception.class)
  public ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) {
    Status statusErr = Status.builder()
        .path(SubStringURIUtils.cutURI(request.getDescription(false)))
        .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
        .message(ex.getMessage())
        .timestamp(LocalDateTime.now().toString())
        .build();
    return new ResponseEntity<>(new GeneralResponse<>(statusErr, null), HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ExceptionHandler(ResourceNotFoundException.class)
  public ResponseEntity<Object> handleResourceNotFoundException(ResourceNotFoundException ex, WebRequest request) {
    Status statusErr = Status.builder()
        .path(SubStringURIUtils.cutURI(request.getDescription(false)))
        .code(HttpStatus.NOT_FOUND.value())
        .message(ex.getMessage())
        .timestamp(LocalDateTime.now().toString())
        .build();
    return new ResponseEntity<>(new GeneralResponse<>(statusErr, null), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(UnAuthorizedException.class)
  public ResponseEntity<Object> handleUnAuthorizedException(UnAuthorizedException ex, WebRequest request) {
    Status statusErr = Status.builder()
        .path(SubStringURIUtils.cutURI(request.getDescription(false)))
        .code(HttpStatus.UNAUTHORIZED.value())
        .message(ex.getMessage())
        .timestamp(LocalDateTime.now().toString())
        .build();
    return new ResponseEntity<>(new GeneralResponse<>(statusErr, null), HttpStatus.UNAUTHORIZED);
  }

  @ExceptionHandler(ResourceAlreadyExistsException.class)
  public ResponseEntity<Object> handleResourceAlreadyExistsException(ResourceAlreadyExistsException ex, WebRequest request) {
    Status statusErr = Status.builder()
        .path(SubStringURIUtils.cutURI(request.getDescription(false)))
        .code(HttpStatus.CONFLICT.value())
        .message(ex.getMessage())
        .timestamp(LocalDateTime.now().toString())
        .build();
    return new ResponseEntity<>(new GeneralResponse<>(statusErr, null), HttpStatus.CONFLICT);
  }
}
