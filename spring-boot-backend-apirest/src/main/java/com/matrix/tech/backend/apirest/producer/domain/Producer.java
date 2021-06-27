package com.matrix.tech.backend.apirest.producer.domain;

import com.matrix.tech.backend.apirest.common.domain.Base;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "PRODUCERS")
public class Producer extends Base implements Serializable {

    private static final long serialVersionUID = 1L;
}
