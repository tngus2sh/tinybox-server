package com.y2h.tinybox.client.movie.service.dto;

import lombok.Builder;

public class MovieDto {
    private String koreanTitle;
    private String englishTitle;
    private String genre;
    private String openDate;
    private String plot;
    private String nation;
    private int runningTime;
    private double avgStar;
    private String ageLimit;

    @Builder
    public MovieDto(String koreanTitle, String englishTitle, String genre, String openDate, String plot, String nation, int runningTime, double avgStar, String ageLimit) {
        this.koreanTitle = koreanTitle;
        this.englishTitle = englishTitle;
        this.genre = genre;
        this.openDate = openDate;
        this.plot = plot;
        this.nation = nation;
        this.runningTime = runningTime;
        this.avgStar = avgStar;
        this.ageLimit = ageLimit;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("MovieDto{");
        sb.append("koreanTitle='").append(koreanTitle).append('\'');
        sb.append(", englishTitle='").append(englishTitle).append('\'');
        sb.append(", genre='").append(genre).append('\'');
        sb.append(", openDate='").append(openDate).append('\'');
        sb.append(", plot='").append(plot).append('\'');
        sb.append(", nation='").append(nation).append('\'');
        sb.append(", runningTime=").append(runningTime);
        sb.append(", avgStar=").append(avgStar);
        sb.append(", ageLimit='").append(ageLimit).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
