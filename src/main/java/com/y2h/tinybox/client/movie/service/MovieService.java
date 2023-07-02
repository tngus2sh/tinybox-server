package com.y2h.tinybox.client.movie.service;

import com.y2h.tinybox.client.movie.service.dto.MovieDetailDto;
import com.y2h.tinybox.client.movie.service.dto.MovieDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Transactional
public interface MovieService {

    /*
        전체조회
     */
    List<MovieDto> getMovie(Map<String, String> map);
    /*
        상세조회
     */
//    List<MovieDetailDto> getByTitle(String title);
//    List<MovieDetailDto> getByPerson(String name);
//    List<MovieDetailDto> getByOpenDate(String startDate, String endDate);
//    List<MovieDetailDto> getByNation(String nation);
//    List<MovieDetailDto> getByAvgStar(double avgStar);
//    List<MovieDetailDto> getByGenre(String genre);
//    List<MovieDetailDto> getByAgeLimit(String ageLimit);

}
