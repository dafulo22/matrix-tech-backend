package com.matrix.tech.backend.apirest.interfaces.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class ExceptionMatrixTechBadRequest extends RuntimeException {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public ExceptionMatrixTechBadRequest(String exception) {
        super(exception);
    }
}
