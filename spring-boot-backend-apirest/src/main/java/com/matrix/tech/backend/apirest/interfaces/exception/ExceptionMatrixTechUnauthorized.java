package com.matrix.tech.backend.apirest.interfaces.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class ExceptionMatrixTechUnauthorized extends RuntimeException {
  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  public ExceptionMatrixTechUnauthorized(String exception) {
    super(exception);
  }
}
