package com.matrix.tech.backend.apirest.common.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"handler", "hibernateLazyInitializer"})
public class Audit implements Serializable {

    private static final long serialVersionUID = 1L;

    @CreatedDate
    @Column(name = "CREATE_AT", updatable = false, nullable = false)
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy", iso = ISO.DATE)
    private Date createAt;

    @LastModifiedDate
    @Column(name = "LAST_UPDATE")
    @JsonIgnore
    @Temporal(TemporalType.DATE)
    @DateTimeFormat(pattern = "dd/MM/yyyy", iso = ISO.DATE)
    private Date lastUpdate;

    @PrePersist
    public void prePersist() {
        this.createAt = new Date();
        this.lastUpdate = new Date();
    }

    @PreUpdate
    public void preUpdate() {
        this.lastUpdate = new Date();
    }

}
