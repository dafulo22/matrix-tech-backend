package com.matrix.tech.backend.apirest.common.domain.enumerated;

import java.util.HashMap;
import lombok.Getter;

@Getter
public enum IdentificationType {

  RC(IdentificationType.RC_CODE, "Registro Civil"), 
  TI(IdentificationType.TI_CODE, "Tarjeta de Identidad"), 
  CC(IdentificationType.CC_CODE, "Cédula de Ciudadanía"), 
  PAS(IdentificationType.PASS_CODE, "Pasaporte"), 
  CE(IdentificationType.CE_CODE, "Cédula de Extranjería"), 
  ND(IdentificationType.ND_CODE, "No Definido");

  private final String id;
  private final String name;

  public static final String RC_CODE = "RC";
  public static final String TI_CODE = "TI";
  public static final String CC_CODE = "CC";
  public static final String PASS_CODE = "PAS";
  public static final String CE_CODE = "CE";
  public static final String ND_CODE = "NA";

  private static final HashMap<String, IdentificationType> ENUM_MAP_BY_CODE = new HashMap<>();

  static {

    ENUM_MAP_BY_CODE.put(RC_CODE, RC);
    ENUM_MAP_BY_CODE.put(TI_CODE, TI);
    ENUM_MAP_BY_CODE.put(TI_CODE, CC);
    ENUM_MAP_BY_CODE.put(TI_CODE, PAS);
    ENUM_MAP_BY_CODE.put(TI_CODE, CE);
    ENUM_MAP_BY_CODE.put(ND_CODE, ND);
  }

  IdentificationType(String id, String name) {
    this.id = id;
    this.name = name;
  }

  public static IdentificationType findByPrimaryKey(String id) {
    if (id != null) {
      return ENUM_MAP_BY_CODE.get(id);
    } else {
      return ND;
    }
  }

  public String getId() {
    return id;
  }

}
