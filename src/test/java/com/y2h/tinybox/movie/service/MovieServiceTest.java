package com.y2h.tinybox.movie.service;

import com.y2h.tinybox.client.movie.Director;
import com.y2h.tinybox.client.movie.Movie;
import com.y2h.tinybox.client.movie.Person;
import com.y2h.tinybox.client.movie.repository.DirectorRepository;
import com.y2h.tinybox.client.movie.repository.MovieRepository;
import com.y2h.tinybox.client.movie.repository.PersonRepository;
import com.y2h.tinybox.client.movie.service.MovieService;
import com.y2h.tinybox.client.movie.service.dto.MovieDetailDto;
import com.y2h.tinybox.client.movie.service.dto.MovieDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.y2h.tinybox.common.Active.ACTIVE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
public class MovieServiceTest {

    @Autowired
    private MovieService movieService;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private DirectorRepository directorRepository;

    @Test
    @DisplayName("영화검색#제목")
    void movieByTitle() {
        //given
        Movie targetMovie = insertMovie();
        Person targetPerson = insertPerson();
        insertDirector(targetMovie, targetPerson);

        //when
        Map<String ,String> map = new HashMap<>();
        map.put("key", "제목");
        map.put("word", "영화좋아");
        List<MovieDto> results = movieService.getMovie(map);

        //then
        for (MovieDto result : results) {
            System.out.println("result = " + result.toString());
        }
        assertThat(results != null && !results.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("영화검색#사람")
    void movieByPerson() {
        //given
        Movie targetMovie = insertMovie();
        Person targetPerson = insertPerson();
        insertDirector(targetMovie, targetPerson);

        //when
        Map<String , String> map = new HashMap<>();
        map.put("key", "배우/감독");
        map.put("word", "훙솽솽");
        List<MovieDto> results = movieService.getMovie(map);

        //then
        for (MovieDto result : results) {
            System.out.println("result = " + result.toString());
        }
        assertThat(results != null && !results.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("영화검색#개봉일")
    void movieByOpenDate() {
        //given
        Movie targetMovie = insertMovie();
        Person targetPerson = insertPerson();
        insertDirector(targetMovie, targetPerson);

        //when
        Map<String , String> map = new HashMap<>();
        map.put("key", "개봉기간");
        map.put("word", "2023.06.23");
        map.put("period", "2023.07.01");
        List<MovieDto> results = movieService.getMovie(map);

        //then
        for (MovieDto result : results) {
            System.out.println("result = " + result);
        }
        assertThat(results != null && !results.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("영화검색#국가")
    void movieByNation() {
        //given
        Movie targetMovie = insertMovie();
        Person targetPerson = insertPerson();
        insertDirector(targetMovie, targetPerson);

        //when
        Map<String , String> map = new HashMap<>();
        map.put("key", "국가");
        map.put("word", "한국");
        List<MovieDto> results = movieService.getMovie(map);

        //then
        for (MovieDto result : results) {
            System.out.println("result = " + result);
        }
        assertThat(results != null && !results.isEmpty()).isTrue();
    }
    @Test
    @DisplayName("영화검색#평균별점")
    void movieByAvgStar() {
        //given
        Movie targetMovie = insertMovie();
        Person targetPerson = insertPerson();
        insertDirector(targetMovie, targetPerson);

        //when
        Map<String , String> map = new HashMap<>();
        map.put("key", "평균별점");
        map.put("word", "3.0");
        List<MovieDto> results = movieService.getMovie(map);

        //then
        for (MovieDto result : results) {
            System.out.println("result = " + result);
        }
        assertThat(results != null && !results.isEmpty()).isTrue();
    }
    @Test
    @DisplayName("영화검색#장르")
    void movieByGenre() {
        //given
        Movie targetMovie = insertMovie();
        Person targetPerson = insertPerson();
        insertDirector(targetMovie, targetPerson);

        //when
        Map<String , String> map = new HashMap<>();
        map.put("key", "장르");
        map.put("word", "멜로");
        List<MovieDto> results = movieService.getMovie(map);

        //then
        for (MovieDto result : results) {
            System.out.println("result = " + result);
        }
        assertThat(results != null && !results.isEmpty()).isTrue();
    }

    @Test
    @DisplayName("영화검색#제한연령")
    void movieByAgeLimit() {
        //given
        Movie targetMovie = insertMovie();
        Person targetPerson = insertPerson();
        insertDirector(targetMovie, targetPerson);

        //when
        Map<String , String> map = new HashMap<>();
        map.put("key", "연령제한");
        map.put("word", "전체관람가");
        List<MovieDto> results = movieService.getMovie(map);

        //then
        for (MovieDto result : results) {
            System.out.println("result = " + result);
        }
        assertThat(results != null && !results.isEmpty()).isTrue();
    }

    private Movie insertMovie() {
        Movie movie = Movie.builder()
                .koreanTitle("영화좋아")
                .englishTitle("like movie")
                .genre("로맨스, 멜로")
                .openDate("2023.06.30")
                .plot("6월의 마지막을 향해 가는...")
                .nation("한국")
                .runningTime(150)
                .avgStar(3.4)
                .ageLimit("전체관람가")
                .posterUploadFileName("fileName")
                .active(ACTIVE)
                .build();
        return movieRepository.save(movie);
    }

    private Person insertPerson() {
        Person person = Person.builder()
                .name("훙솽솽")
                .birth("1998.05.17")
                .nation("한국")
                .active(ACTIVE)
                .build();
        return personRepository.save(person);
    }

    private Director insertDirector(Movie movie, Person person) {
        Director director = Director.builder()
                .movie(movie)
                .person(person)
                .type("배우")
                .build();
        return directorRepository.save(director);
    }
}
