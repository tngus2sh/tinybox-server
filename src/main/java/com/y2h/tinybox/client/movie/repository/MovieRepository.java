package com.y2h.tinybox.client.movie.repository;

import com.y2h.tinybox.client.movie.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {
    /*
        영화 검색 - 전체 목록 조회
     */
    /**
     * 영화 전체 목록 조회
     * @return List<Movie>
     */
    @Query("select m from Movie m")
    List<Movie> getMovies();

    /**
     * 영화 제목으로 검색
     * @param title 영화 제목
     * @return List<Movie>
     */
    @Query("select m from Movie m where m.koreanTitle like %:title% or m.englishTitle like %:title%")
    List<Movie> getMoviesByTitle(@Param("title") String title);

    /**
     * 사람(배우,감독)으로 조회
     * @param name 사람 이름
     * @return List<Object[]>
     */
    @Query("select distinct m from Director d right join d.movie m left join d.person p where p.name = :name")
    List<Movie> getMoviesByPerson(@Param("name") String name);

    /**
     * 개봉 기간으로 조회
     * @param startDate 개봉 시작
     * @param endDate 개봉 끝
     * @return List<Movie>
     */
    @Query("select m from Movie m where m.openDate between :startDate and :endDate")
    List<Movie> getMoviesByOpenDate(@Param("startDate") String startDate, @Param("endDate") String endDate);

    /**
     * 나라로 조회
     * @param nation 나라
     * @return List<Movie>
     */
    @Query("select m from Movie m where m.nation = :nation")
    List<Movie> getMoviesByNation(@Param("nation") String nation);

    /**
     * 평균 별점으로 조회
     * @param avgStar 평균 별점
     * @return List<Movie>
     */
    @Query("select m from Movie m where m.avgStar >= :avgStar")
    List<Movie> getMoviesByAvgStar(@Param("avgStar") double avgStar);

    /**
     * 장르로 조회
     * @param genre 장르
     * @return List<Movie>
     */
    @Query("select m from Movie m where m.genre like %:genre%")
    List<Movie> getMoviesByGenre(@Param("genre") String genre);

    /**
     * 나이제한으로 조회
     * @param ageLimit 나이제한
     * @return List<Movie>
     */
    @Query("select m, d, p from Director d right join d.movie m left join d.person p where m.ageLimit like %:ageLimit%")
    List<Movie> getMoviesByAgeLimit(@Param("ageLimit") String ageLimit);

}
