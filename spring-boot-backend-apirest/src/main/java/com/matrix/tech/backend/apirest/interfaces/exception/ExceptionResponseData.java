package com.matrix.tech.backend.apirest.interfaces.exception;

import lombok.Data;

@Data
public class ExceptionResponseData {

  private int status;
  private String message;
}
