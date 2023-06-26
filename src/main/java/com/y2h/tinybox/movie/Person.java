package com.y2h.tinybox.movie;

import com.y2h.tinybox.common.Active;
import com.y2h.tinybox.common.TimeBaseEntity;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;

@Entity
@Getter
public class Person extends TimeBaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "person_id")
    Long id;
    @Column(length = 100)
    private String name;
    @Column(length = 10)
    private String birth;
    @Column(length = 50)
    private String nation;
    @Enumerated(STRING)
    @Column(nullable = false, length = 20)
    private Active active;

    public Person() {
    }

    @Builder
    public Person(Long id, String name, String birth, String nation, Active active) {
        this.id = id;
        this.name = name;
        this.birth = birth;
        this.nation = nation;
        this.active = active;
    }
}
