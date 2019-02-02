package com.dwg.home.exception;

import java.util.Arrays;
import java.util.List;

import org.springframework.http.HttpStatus;

public class ApiError {
  private HttpStatus status;
  private String message;
  private List<String> errors;

  /**
   * Constructor for ApiError with multiple errors.
   * 
   * @param status The HttpStatus
   * @param message The error message
   * @param errors The List of errors
   */
  public ApiError(HttpStatus status, String message, List<String> errors) {
    super();
    this.status = status;
    this.message = message;
    this.errors = errors;
  }

  /**
   * Constructor for ApiError for a single error.
   * 
   * @param status The HttpStatus
   * @param message The error message
   * @param error The error
   */
  public ApiError(HttpStatus status, String message, String error) {
    super();
    this.status = status;
    this.message = message;
    errors = Arrays.asList(error);
  }

  public HttpStatus getStatus() {
    return status;
  }

  public void setStatus(HttpStatus status) {
    this.status = status;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public List<String> getErrors() {
    return errors;
  }

  public void setErrors(List<String> errors) {
    this.errors = errors;
  }
}