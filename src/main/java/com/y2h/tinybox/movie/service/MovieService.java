package com.y2h.tinybox.movie.service;

import com.y2h.tinybox.movie.service.dto.MovieDetailDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface MovieService {

    List<MovieDetailDto> getByTitle(String title);
    List<MovieDetailDto> getByPerson(String name);
    List<MovieDetailDto> getByOpenDate(String startDate, String endDate);
    List<MovieDetailDto> getByNation(String nation);
    List<MovieDetailDto> getByAvgStar(double avgStar);
    List<MovieDetailDto> getByGenre(String genre);
    List<MovieDetailDto> getByAgeLimit(String ageLimit);

}
