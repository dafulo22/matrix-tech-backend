package com.matrix.tech.backend.apirest.director.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.matrix.tech.backend.apirest.common.domain.Base;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "DIRECTORS")
public class Director extends Base implements Serializable {

  private static final long serialVersionUID = 1L;
}
