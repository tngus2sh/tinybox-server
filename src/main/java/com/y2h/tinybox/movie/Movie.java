package com.y2h.tinybox.movie;

import com.y2h.tinybox.common.Active;
import com.y2h.tinybox.common.TimeBaseEntity;
import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;

import static javax.persistence.EnumType.STRING;

@Entity
@Getter
public class Movie extends TimeBaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "movie_id")
    Long id;
    @Column(length = 100)
    private String koreanTitle;
    @Column(length = 100)
    private String englishTitle;
    @Column(length = 200)
    private String genre;
    @Column(length = 10)
    private String openDate;
    @Column
    private String plot;
    @Column(length = 100)
    private String nation;
    @Column
    private int runningTime;
    @Column
    private double avgStar;
    @Column
    private String ageLimit;
    @Column
    private String posterUploadFileName;
    @Column
    private String posterStoreFileName;
    @Enumerated(STRING)
    @Column(nullable = false, length = 20)
    private Active active;

    public Movie() {

    }
    @Builder
    public Movie(Long id, String koreanTitle, String englishTitle, String genre, String openDate, String plot, String nation, int runningTime, double avgStar, String ageLimit, String posterUploadFileName, String posterStoreFileName, Active active) {
        this.id = id;
        this.koreanTitle = koreanTitle;
        this.englishTitle = englishTitle;
        this.genre = genre;
        this.openDate = openDate;
        this.plot = plot;
        this.nation = nation;
        this.runningTime = runningTime;
        this.avgStar = avgStar;
        this.ageLimit = ageLimit;
        this.posterUploadFileName = posterUploadFileName;
        this.posterStoreFileName = posterStoreFileName;
        this.active = active;
    }
}
