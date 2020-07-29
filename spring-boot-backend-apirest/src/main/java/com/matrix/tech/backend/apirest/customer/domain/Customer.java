package com.matrix.tech.backend.apirest.customer.domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import com.matrix.tech.backend.apirest.common.domain.Person;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "CUSTOMERS")
public class Customer extends Person implements Serializable {

  private static final long serialVersionUID = 1L;
}
