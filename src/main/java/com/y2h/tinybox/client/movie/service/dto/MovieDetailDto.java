package com.y2h.tinybox.client.movie.service.dto;

import lombok.Builder;

import java.util.List;

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
    private List<PersonDto> personDtoList;

    public MovieDetailDto() {
    }

    @Builder
    public MovieDetailDto(String koreanTitle, String englishTitle, String genre, String openDate, String plot, String nation, int runningTime, double avgStar, String ageLimit, String posterUploadFileName, String posterStoreFileName, List<PersonDto> personDtoList) {
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
        this.personDtoList = personDtoList;
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
        sb.append(", personDtoList=").append(personDtoList);
        sb.append('}');
        return sb.toString();
    }
}
