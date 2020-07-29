package com.matrix.tech.backend.apirest.common.domain;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.matrix.tech.backend.apirest.common.domain.enumerated.Gender;
import com.matrix.tech.backend.apirest.common.domain.enumerated.IdentificationType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@MappedSuperclass
@EqualsAndHashCode(callSuper = false)
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Person extends Base implements Serializable {

  /**
   * 
   */
  private static final long serialVersionUID = 1L;

  @Enumerated(EnumType.STRING)
  @Column(name = "identification_type", length = 3)
  @NotNull
  private IdentificationType identificationType;

  @NotEmpty
  @NotNull
  @Column(length = 30, unique = true)
  private String document;

  @Column(name = "LAST_NAME", length = 40)
  private String lastName;

  @Email
  @Column(length = 50)
  private String email;

  @Column(length = 100)
  private String address;

  @Column(length = 15)
  private String phone;

  @Column(length = 15)
  private String cellPhone;

  @NotNull
  @Enumerated(EnumType.STRING)
  @Column(length = 2)
  private Gender gender;

  @Temporal(TemporalType.DATE)
  @DateTimeFormat(pattern = "dd/MM/yyyy")
  @JsonFormat(pattern = "dd/MM/yyyy")
  private Date birthDate;

  @Override
  public String toString() {
    return getName() + " " + getLastName();
  }
}
