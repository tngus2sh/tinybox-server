package com.y2h.tinybox.movie;

import com.y2h.tinybox.common.TimeBaseEntity;
import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Getter
public class Director extends TimeBaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "director_id")
    Long id;
    @Column
    Long movie_id;
    @Column
    Long person_id;
    @Column(length = 10)
    String type;

    public Director() {
    }

    public Director(Long id, Long movie_id, Long person_id, String type) {
        this.id = id;
        this.movie_id = movie_id;
        this.person_id = person_id;
        this.type = type;
    }
}
