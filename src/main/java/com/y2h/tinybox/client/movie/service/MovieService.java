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
    MovieDetailDto getMovieDetail(Long movieId);

}
