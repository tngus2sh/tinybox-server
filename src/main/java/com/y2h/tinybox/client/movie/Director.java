package com.y2h.tinybox.client.movie;

import com.y2h.tinybox.common.TimeBaseEntity;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Director extends TimeBaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "director_id")
    Long id;
    @JoinColumn(name = "movie_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    Movie movie;
    @JoinColumn(name = "person_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    Person person;
    @Column(length = 10)
    String type;

    public Director() {
    }

    @Builder
    public Director(Long id, Movie movie, Person person, String type) {
        this.id = id;
        this.movie = movie;
        this.person = person;
        this.type = type;
    }
}
