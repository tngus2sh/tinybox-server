package com.y2h.tinybox.movie.service;

import com.y2h.tinybox.movie.Movie;
import com.y2h.tinybox.movie.repository.MovieRepository;
import com.y2h.tinybox.movie.service.dto.MovieDetailDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static com.y2h.tinybox.common.Active.ACTIVE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

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
        Movie targetMovie = insertMovie();

        //when
        MovieDetailDto dto = MovieDetailDto.builder()
                .koreanTitle("테스트중입니다.")
                .build();

        //then
        // 검색 결과 없다는 오류?
//        assertThatThrownBy(() -> movieService.searchByTitle(dto)
//                .isInstatnceOf());
    }

    private Movie insertMovie() {
        Movie movie = Movie.builder()
                .koreanTitle("영화좋아")
                .englishTitle("like movie")
                .openDate("2023.06.30")
                .plot("6월의 마지막을 향해 가는...")
                .nation("한국")
                .runningTime(150)
                .avgStar(3.4)
                .posterUploadFileName("fileName")
                .active(ACTIVE)
                .build();
        return movieRepository.save(movie);
    }
}
