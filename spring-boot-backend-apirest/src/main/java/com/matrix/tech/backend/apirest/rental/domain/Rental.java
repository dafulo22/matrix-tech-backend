package com.matrix.tech.backend.apirest.rental.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.matrix.tech.backend.apirest.customer.domain.Customer;
import com.matrix.tech.backend.apirest.inventory.domain.Inventory;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "RENTAL")
public class Rental implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private Date rentalDate;

    @DateTimeFormat(pattern = "dd/MM/yyyy")
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    private Date returnDate;

    /*
     * CLIENTE
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false,
        foreignKey = @ForeignKey(name = "FK_CUSTOMER_ID"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Customer customer;

    /*
     * INVENTARIO
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "inventory_id", nullable = false,
        foreignKey = @ForeignKey(name = "FK_INVENTORY_ID"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    private Inventory inventory;

    private BigDecimal price;

}
