package com.y2h.tinybox.client.movie.service.dto;

import lombok.Builder;

public class MovieDetailDto {

    private String koreanTitle;
    private String englishTitle;
    private String genre;
    private String openDate;
    private String plot;
    private String nation;
    private int runningTime;
    private double avgStar;
    private String ageLimit;
    private String posterUploadFileName;
    private String posterStoreFileName;
    private String personName;
    private String personBirth;
    private String personNation;
    private String personType;

    @Builder
    public MovieDetailDto(String koreanTitle, String englishTitle, String genre, String openDate, String plot, String nation, int runningTime, double avgStar, String ageLimit, String posterUploadFileName, String posterStoreFileName, String personName, String personBirth, String personNation, String personType) {
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
        this.personName = personName;
        this.personBirth = personBirth;
        this.personNation = personNation;
        this.personType = personType;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("MovieDetailDto{");
        sb.append("koreanTitle='").append(koreanTitle).append('\'');
        sb.append(", englishTitle='").append(englishTitle).append('\'');
        sb.append(", genre='").append(genre).append('\'');
        sb.append(", openDate='").append(openDate).append('\'');
        sb.append(", plot='").append(plot).append('\'');
        sb.append(", nation='").append(nation).append('\'');
        sb.append(", runningTime=").append(runningTime);
        sb.append(", avgStar=").append(avgStar);
        sb.append(", ageLimit='").append(ageLimit).append('\'');
        sb.append(", posterUploadFileName='").append(posterUploadFileName).append('\'');
        sb.append(", posterStoreFileName='").append(posterStoreFileName).append('\'');
        sb.append(", personName='").append(personName).append('\'');
        sb.append(", personBirth='").append(personBirth).append('\'');
        sb.append(", personNation='").append(personNation).append('\'');
        sb.append(", personType='").append(personType).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
