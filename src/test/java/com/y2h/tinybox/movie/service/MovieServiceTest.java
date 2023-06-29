package com.y2h.tinybox.movie.service;

import com.y2h.tinybox.movie.repository.MovieRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
public class MovieServiceTest {

    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieRepository movieRepository;

    @Test
    @DisplayName("영화제목검색#결과 없음")
    void noContentMovieByTitle() {
        //given
        //when
        //then
    }
}
