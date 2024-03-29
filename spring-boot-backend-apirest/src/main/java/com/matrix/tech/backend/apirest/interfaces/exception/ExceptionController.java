package com.matrix.tech.backend.apirest.interfaces.exception;

import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Exception manager
 *
 * @author DANIEL FUENTES
 */
@Component
@ControllerAdvice
@Slf4j
public class ExceptionController {

    private static final String REQUEST = "Request: ";

    @ExceptionHandler(value = {IllegalArgumentException.class, IllegalStateException.class})
    protected ResponseEntity<ExceptionResponseData> handleArgumentError(HttpServletRequest req,
        Exception e) {
        log.error(REQUEST + req.getRequestURL() + " raised ", e);
        ExceptionResponseData exceptionResponseData =
            getExceptionResponseData(HttpStatus.CONFLICT, e.getMessage());
        return new ResponseEntity<>(exceptionResponseData, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    protected ResponseEntity<ExceptionResponseData> handleMethodArgumentNotValid(
        HttpServletRequest req, MethodArgumentNotValidException e) {
        log.error(REQUEST + req.getRequestURL() + " raised ", e);

        BindingResult result = e.getBindingResult();
        List<FieldError> fieldErrors = result.getFieldErrors();

        String errors = fieldErrors.stream().map(error -> error.getDefaultMessage())
            .collect(Collectors.joining(", "));

        ExceptionResponseData exceptionResponseData =
            getExceptionResponseData(HttpStatus.CONFLICT, errors);

        return new ResponseEntity<>(exceptionResponseData, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ExceptionResponseData> handleError(HttpServletRequest req, Exception e) {
        log.error(REQUEST + req.getRequestURL() + " raised ", e);

        ExceptionResponseData exceptionResponseData =
            getExceptionResponseData(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());

        return new ResponseEntity<>(exceptionResponseData,
            HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = ExceptionMatrixTechBadRequest.class)
    public ResponseEntity<ExceptionResponseData> handleError(HttpServletRequest req,
        ExceptionMatrixTechBadRequest e) {
        log.error(REQUEST + req.getRequestURL() + " raised ", e);

        ExceptionResponseData exceptionResponseData =
            getExceptionResponseData(HttpStatus.BAD_REQUEST, e.getMessage());

        return new ResponseEntity<>(exceptionResponseData, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ExceptionMatrixTechUnauthorized.class)
    public ResponseEntity<ExceptionResponseData> handleError(HttpServletRequest req,
        ExceptionMatrixTechUnauthorized e) {
        log.error(REQUEST + req.getRequestURL() + " raised ", e);

        ExceptionResponseData exceptionResponseData =
            getExceptionResponseData(HttpStatus.UNAUTHORIZED, e.getMessage());

        return new ResponseEntity<>(exceptionResponseData,
            HttpStatus.UNAUTHORIZED);
    }

    /**
     * @param status
     * @param message
     * @return
     */
    private ExceptionResponseData getExceptionResponseData(HttpStatus status, String message) {
        ExceptionResponseData exceptionResponseData = new ExceptionResponseData();
        exceptionResponseData.setStatus(status.value());
        exceptionResponseData.setMessage(message);
        return exceptionResponseData;
    }

}
