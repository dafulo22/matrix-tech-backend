package com.matrix.tech.backend.apirest.technology.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.matrix.tech.backend.apirest.common.domain.Base;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "TECHNOLOGIES")
public class Technology extends Base implements Serializable {

  private static final long serialVersionUID = 1L;
}
