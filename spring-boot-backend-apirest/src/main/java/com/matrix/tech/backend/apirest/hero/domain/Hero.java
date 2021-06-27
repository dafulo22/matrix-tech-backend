package com.matrix.tech.backend.apirest.hero.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.matrix.tech.backend.apirest.common.domain.Base;
import com.matrix.tech.backend.apirest.game.domain.Game;
import java.io.Serializable;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(exclude = "games")
@Table(name = "HERO")
public class Hero extends Base implements Serializable {

    private static final long serialVersionUID = 1L;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL,
        mappedBy = "hero")
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonBackReference
    private List<Game> games;

}
