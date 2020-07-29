package com.matrix.tech.backend.apirest.common;

import static com.matrix.tech.backend.apirest.common.constants.Constant.CREATE;
import static com.matrix.tech.backend.apirest.common.constants.Constant.DELETE;
import static com.matrix.tech.backend.apirest.common.constants.Constant.READ;
import static com.matrix.tech.backend.apirest.common.constants.Constant.UPDATE;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import javax.validation.Valid;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import com.matrix.tech.backend.apirest.common.domain.Base;

public final class Util {

  public static ResponseEntity<Map<String, Object>> obtainErrorResponse(BindingResult result) {
    Map<String, Object> response = new HashMap<>();

    List<String> errors = result.getFieldErrors().stream()
        .map(err -> "El campo '" + err.getField() + "' " + err.getDefaultMessage())
        .collect(Collectors.toList());

    response.put("errors", errors);
    return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
  }

  public static boolean hasErrors(BindingResult result) {

    return (result.hasErrors());

  }

  public static boolean verifyResponse(ResponseEntity<?> responseEntity) {
    return responseEntity.getStatusCode().equals(HttpStatus.OK);
  }

  public static Map<String, Object> obtainResponseError(DataAccessException e, String code) {

    Map<String, Object> response = new HashMap<>();
    response.put("mensaje", getMessageByCode(code));
    response.put("error",
        e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
    return response;
  }

  private static String getMessageByCode(String code) {

    switch (code) {
      case CREATE:
        return "Error al realizar el insert en la base de datos";
      case READ:
        return "Error al realizar la consulta en la base de datos";
      case UPDATE:
        return "Error al actualizar el registro en la base de datos";
      case DELETE:
        return "Error al eliminar registro de la base de datos";
    }

    return code;
  }

  public static void fillInDefaultInformation(@Valid Base baseParam, Base currentBase) {
    currentBase.setCode(baseParam.getCode());
    currentBase.setDescription(baseParam.getDescription());
    currentBase.setName(baseParam.getName());
  }

  public static LocalDateTime stringConvertToLocalDataTime(String stringConvertToLocalDataTime)
      throws ParseException {

    try {
      LocalDateTime local = null;
      SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
      Date fechaDate = null;
      fechaDate = (Date) formato.parse(stringConvertToLocalDataTime);
      local = fechaDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime();
      return local;
    } catch (final DateTimeParseException e) {
      return null;
    }
  }

}
