package com.y2h.tinybox.client.movie.service.impl;

import com.y2h.tinybox.client.movie.Movie;
import com.y2h.tinybox.client.movie.Person;
import com.y2h.tinybox.client.movie.repository.DirectorRepository;
import com.y2h.tinybox.client.movie.repository.MovieRepository;
import com.y2h.tinybox.client.movie.service.MovieService;
import com.y2h.tinybox.client.movie.Director;
import com.y2h.tinybox.client.movie.service.dto.MovieDetailDto;
import com.y2h.tinybox.client.movie.service.dto.MovieDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    private final DirectorRepository directorRepository;

    /**
     * 영화 목록 조회
     * @param map key - 키워드, word - 검색어(개봉기간 검색 시 시작기간), period - 개봉기간 검색 시 종료기간
     * @return List<MovieDto>
     */
    @Override
    public List<MovieDto> getMovie(Map<String, String> map) {
        List<Movie> results = null;
        String key = map.get("key");
        String word = map.get("word");
        // key가 없을 때 전체 검색
        if (key == null) {
            results = movieRepository.getMovies();
        }
        // key가 제목 검색일 때
        else if (key.equals("제목")) {
            results = movieRepository.getMoviesByTitle(word);
        }
        // key가 배우 검색일 때
        else if (key.equals("배우/감독")) {
            results = movieRepository.getMoviesByPerson(word);
        }
        // key가 개봉기간 검색일 때
        else if (key.equals("개봉기간")) {
            String period = map.get("period");
            results = movieRepository.getMoviesByOpenDate(word, period);
        }
        // key가 국가 검색일 때
        else if (key.equals("국가")) {
            results = movieRepository.getMoviesByNation(word);
        }
        // key가 평균별점 검색일 때
        else if (key.equals("평균별점")) {
            results = movieRepository.getMoviesByAvgStar(Double.parseDouble(word));
        }
        // key가 장르 검색일 때
        else if (key.equals("장르")) {
            results = movieRepository.getMoviesByGenre(word);
        }
        // key가 연령제한 검색일 때
        else if (key.equals("연령제한")) {
            results = movieRepository.getMoviesByAgeLimit(word);
        }
        return getMovieList(results);
    }

    /**
     * 영화 상세 정보 출력
     * @param movieId 영화 식별 번호
     * @return List<MovieDetailDto>
     */
    @Override
    public List<MovieDetailDto> getMovieDetail(Long movieId) {
        Movie movie = movieRepository.getMoviesById(movieId);
        List<Object[]> results = directorRepository.getPersonDirectors(movie);

        List<MovieDetailDto> movieDetailDtos = new ArrayList<>();
        for (Object[] result : results) {
            Director director = (Director) result[0];
            Person person = (Person) result[1];

            movieDetailDtos.add(MovieDetailDto.builder()
                    .koreanTitle(movie.getKoreanTitle())
                    .englishTitle(movie.getEnglishTitle())
                    .openDate(movie.getOpenDate())
                    .genre(movie.getGenre())
                    .plot(movie.getPlot())
                    .nation(movie.getNation())
                    .runningTime(movie.getRunningTime())
                    .avgStar(movie.getAvgStar())
                    .ageLimit(movie.getAgeLimit())
                    .posterStoreFileName(movie.getPosterStoreFileName())
                    .personName(person.getName())
                    .personBirth(person.getBirth())
                    .personNation(person.getNation())
                    .personType(director.getType())
                    .build());
        }
        return movieDetailDtos;
    }

    /**
     * 영화 리스트 반환 - 전체조회
     * @param results List<Movie> 형태의 인자
     * @return List<MovieDto>
     */
    public List<MovieDto> getMovieList(List<Movie> results) {
        List<MovieDto> movieDtos = new ArrayList<>();
        for (Movie result : results) {
            Movie movie = (Movie) result;

            movieDtos.add(MovieDto.builder()
                    .koreanTitle(movie.getKoreanTitle())
                    .englishTitle(movie.getEnglishTitle())
                    .openDate(movie.getOpenDate())
                    .genre(movie.getGenre())
                    .plot(movie.getPlot())
                    .nation(movie.getNation())
                    .runningTime(movie.getRunningTime())
                    .avgStar(movie.getAvgStar())
                    .ageLimit(movie.getAgeLimit())
                    .build());
        }
        return movieDtos;
    }
    /**
     * 영화 리스트 반환 - 상세조회
     * @param results List<Object[]> 형태의 인자
     * @return List<MovieDetailDto>
     */
    public List<MovieDetailDto> getMoviesList(List<Object[]> results) {
        List<MovieDetailDto> movieDetailDtos = new ArrayList<>();
        for (Object[] result : results) {
            Movie movie = (Movie) result[0];
            Director director = (Director) result[1];
            Person person = (Person) result[2];

            movieDetailDtos.add(MovieDetailDto.builder()
                    .koreanTitle(movie.getKoreanTitle())
                    .englishTitle(movie.getEnglishTitle())
                    .openDate(movie.getOpenDate())
                    .genre(movie.getGenre())
                    .plot(movie.getPlot())
                    .nation(movie.getNation())
                    .runningTime(movie.getRunningTime())
                    .avgStar(movie.getAvgStar())
                    .ageLimit(movie.getAgeLimit())
                    .posterStoreFileName(movie.getPosterStoreFileName())
                    .personName(person.getName())
                    .personBirth(person.getBirth())
                    .personNation(person.getNation())
                    .personType(director.getType())
                    .build());
        }
        return movieDetailDtos;
    }
}
