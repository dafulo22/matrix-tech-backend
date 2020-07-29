package com.matrix.tech.backend.apirest.game.domain;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.matrix.tech.backend.apirest.common.domain.Base;
import com.matrix.tech.backend.apirest.director.domain.Director;
import com.matrix.tech.backend.apirest.hero.domain.Hero;
import com.matrix.tech.backend.apirest.producer.domain.Producer;
import com.matrix.tech.backend.apirest.technology.domain.Technology;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@Table(name = "GAMES")
public class Game extends Base implements Serializable {

  private String year;

  /*
   * PROTAGONISTAS
   */
  @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  @JoinTable(name = "game_hero", joinColumns = {@JoinColumn(name = "game_id")},
      inverseJoinColumns = {@JoinColumn(name = "hero_id")})
  @JsonManagedReference
  private Collection<Hero> hero = new ArrayList<Hero>();

  /*
   * DIRECTOR
   */
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "director_id", nullable = false,
      foreignKey = @ForeignKey(name = "FK_DIRECTOR_ID"))
  @OnDelete(action = OnDeleteAction.CASCADE)
  private Director director;

  /*
   * PRODUCTOR
   */
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "producer_id", nullable = false,
      foreignKey = @ForeignKey(name = "FK_PRODUCER_ID"))
  @OnDelete(action = OnDeleteAction.CASCADE)
  private Producer producer;

  /*
   * TECNOLOGÍA
   */
  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "technology_id", nullable = false,
      foreignKey = @ForeignKey(name = "FK_TECHNOLOGY_ID"))
  @OnDelete(action = OnDeleteAction.CASCADE)
  private Technology technology;

  private static final long serialVersionUID = 1L;
}
