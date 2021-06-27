package com.matrix.tech.backend.apirest.inventory.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.matrix.tech.backend.apirest.game.domain.Game;
import java.io.Serializable;
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

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "INVENTORY")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Inventory implements Serializable {

    private static final long serialVersionUID = 1L;
    Boolean available;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /*
     * JUEGO
     */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "game_id", nullable = false, foreignKey = @ForeignKey(name = "FK_GAMER_ID"))
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Game game;
}
