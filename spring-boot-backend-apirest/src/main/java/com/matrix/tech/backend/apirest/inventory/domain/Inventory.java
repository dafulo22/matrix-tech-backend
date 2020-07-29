package com.matrix.tech.backend.apirest.inventory.domain;

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
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.matrix.tech.backend.apirest.game.domain.Game;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "INVENTORY")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Inventory implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  Boolean available;

  /*
   * JUEGO
   */
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "game_id", nullable = false, foreignKey = @ForeignKey(name = "FK_GAMER_ID"))
  @OnDelete(action = OnDeleteAction.CASCADE)
  @JsonIgnore  
  private Game game;

  private static final long serialVersionUID = 1L;
}
