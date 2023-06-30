package com.y2h.tinybox.movie.service.dto;

import lombok.Builder;

public class MovieDetailDto {

    private String koreanTitle;
    private String englishTitle;
    private String openDate;
    private String plot;
    private String nation;
    private int runningTime;
    private double avgStar;
    private String posterUploadFileName;
    private String posterStoreFileName;
    private String personName;
    private String personBirth;
    private String personNation;
    private String personType;

    @Builder
    public MovieDetailDto(String koreanTitle, String englishTitle, String openDate, String plot, String nation, int runningTime, double avgStar, String posterUploadFileName, String posterStoreFileName, String personName, String personBirth, String personNation, String personType) {
        this.koreanTitle = koreanTitle;
        this.englishTitle = englishTitle;
        this.openDate = openDate;
        this.plot = plot;
        this.nation = nation;
        this.runningTime = runningTime;
        this.avgStar = avgStar;
        this.posterUploadFileName = posterUploadFileName;
        this.posterStoreFileName = posterStoreFileName;
        this.personName = personName;
        this.personBirth = personBirth;
        this.personNation = personNation;
        this.personType = personType;
    }
}
