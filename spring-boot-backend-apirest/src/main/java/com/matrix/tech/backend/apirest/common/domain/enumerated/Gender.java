package com.matrix.tech.backend.apirest.common.domain.enumerated;

import java.util.HashMap;
import lombok.Getter;

@Getter
public enum Gender {
  F(Gender.FEMALE_CODE, "Femenino"), 
  M(Gender.MALE_CODE, "Masculino"), 
  ND(Gender.ND_CODE, "No Definido");

  private final String id;
  private final String name;

  public static final String FEMALE_CODE = "F";
  public static final String MALE_CODE = "M";
  public static final String ND_CODE = "NA";

  private static final HashMap<String, Gender> ENUM_MAP_BY_CODE = new HashMap<>();

  static {

    ENUM_MAP_BY_CODE.put(FEMALE_CODE, F);
    ENUM_MAP_BY_CODE.put(MALE_CODE, M);
    ENUM_MAP_BY_CODE.put(ND_CODE, ND);
  }

  Gender(String id, String name) {
    this.id = id;
    this.name = name;
  }

  public static Gender findByPrimaryKey(String id) {
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
