package com.y2h.tinybox.client.movie.service.impl;

import com.y2h.tinybox.client.movie.Movie;
import com.y2h.tinybox.client.movie.Person;
import com.y2h.tinybox.client.movie.repository.MovieRepository;
import com.y2h.tinybox.client.movie.service.MovieService;
import com.y2h.tinybox.client.movie.Director;
import com.y2h.tinybox.client.movie.service.dto.MovieDetailDto;
import com.y2h.tinybox.client.movie.service.dto.MovieDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    @Override
    public List<MovieDto> getMovieByTitle(String title) {
        List<Movie> results = movieRepository.getMoviesByTitle(title);
        return getMovieList(results);
    }

    @Override
    public List<MovieDetailDto> getMovieByPerson(String name) {
        List<Object[]> results = movieRepository.getMoviesByPerson(name);
        return getMoviesList(results);
    }

    @Override
    public List<MovieDto> getMovieByOpenDate(String startDate, String endDate) {
        List<Movie> results = movieRepository.getMoviesByOpenDate(startDate, endDate);
        return getMovieList(results);
    }

    @Override
    public List<MovieDto> getMovieByNation(String nation) {
        List<Movie> results = movieRepository.getMoviesByNation(nation);
        return getMovieList(results);
    }

    @Override
    public List<MovieDto> getMovieByAvgStar(double avgStar) {
        List<Movie> results = movieRepository.getMoviesByAvgStar(avgStar);
        return getMovieList(results);
    }

    @Override
    public List<MovieDto> getMovieByGenre(String genre) {
        List<Movie> results = movieRepository.getMoviesByGenre(genre);
        return getMovieList(results);
    }

    @Override
    public List<MovieDto> getMovieByAgeLimit(String ageLimit) {
        List<Movie> results = movieRepository.getMoviesByAgeLimit(ageLimit);
        return getMovieList(results);
    }

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
//
//    /**
//     * 제목으로 영화 찾기
//     *
//     * @param title 제목
//     * @return List<MovieDetailDto> 영화 리스트
//     */
//    @Override
//    public List<MovieDetailDto> getByTitle(String title) {
////        List<Object[]> results = movieRepository.getMoviesByTitle(title);
////        return getMovieList(results);
//        return null;
//    }
//
//    /**
//     * 사람으로 영화 찾기
//     * @param name 사람(배우, 감독)
//     * @return List<MovieDetailDto> 영화 리스트
//     */
//    @Override
//    public List<MovieDetailDto> getByPerson(String name) {
//        List<Object[]> results = movieRepository.getMoviesByPerson(name);
//        return getMoviesList(results);
//    }
//
//    /**
//     * 개봉기간으로 영화 찾기
//     * @param startDate 개봉기간 시작
//     * @param endDate 개봉기간 끝
//     * @return List<MovieDetailDto> 영화 리스트
//     */
//    @Override
//    public List<MovieDetailDto> getByOpenDate(String startDate, String endDate) {
//        List<Object[]> results = movieRepository.getMoviesByOpenDate(startDate, endDate);
//        return getMoviesList(results);
//    }
//
//    /**
//     * 개봉 나라로 영화 찾기
//     * @param nation 나라
//     * @return List<MovieDetailDto> 영화 리스트
//     */
//    @Override
//    public List<MovieDetailDto> getByNation(String nation) {
//        List<Object[]> results = movieRepository.getMoviesByNation(nation);
//        return getMoviesList(results);
//    }
//
//    /**
//     * 평균 별점으로 영화 찾기
//     * @param avgStar 평균 별점
//     * @return List<MovieDetailDto> 영화 리스트
//     */
//    @Override
//    public List<MovieDetailDto> getByAvgStar(double avgStar) {
//        List<Object[]> results = movieRepository.getMoviesByAvgStar(avgStar);
//        return getMoviesList(results);
//    }
//
//    /**
//     * 장르로 영화 찾기
//     * @param genre 장르
//     * @return List<MovieDetailDto> 영화 리스트
//     */
//    @Override
//    public List<MovieDetailDto> getByGenre(String genre) {
//        List<Object[]> results = movieRepository.getMoviesByGenre(genre);
//        return getMoviesList(results);
//    }
//
//    /**
//     * 나이 제한으로 영화 찾기
//     * @param ageLimit 나이 제한
//     * @return List<MovieDetailDto> 영화 리스트
//     */
//    @Override
//    public List<MovieDetailDto> getByAgeLimit(String ageLimit) {
//        List<Object[]> results = movieRepository.getMoviesByAgeLimit(ageLimit);
//        return getMoviesList(results);
//    }

    /**
     * 영화 리스트 반환
     * @param results List<Object[]> 형태의 리스트 인자
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
