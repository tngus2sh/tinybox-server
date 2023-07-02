package com.y2h.tinybox.movie.repository;

import com.y2h.tinybox.movie.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    /**
     * 영화 제목으로 조회
     * @param title 영화 제목
     * @return List<Object[]>
     */
    @Query("select m, d, p from Movie m left join Director d on m.id = d.movie_id left join Person p on d.person_id = p.id where m.koreanTitle like %:title% or m.englishTitle like %:title%")
    List<Object[]> getMoviesByTitle(@Param("title") String title);

    /**
     * 사람(배우,감독)으로 조회
     * @param name 사람 이름
     * @return List<Object[]>
     */
    @Query("select m, d, p from Movie m left join Director d on m.id = d.movie_id left join Person p on d.person_id = p.id where p.name = :name")
    List<Object[]> getMoviesByPerson(@Param("name") String name);

    /**
     * 개봉 기간으로 조회
     * @param startDate 개봉 시작
     * @param endDate 개봉 끝
     * @return List<Object[]>
     */
    @Query("select m, d, p from Movie m left join Director d on m.id = d.movie_id left join Person p on d.person_id = p.id where m.openDate between :startDate and :endDate")
    List<Object[]> getMoviesByOpenDate(@Param("startDate") String startDate, @Param("endDate") String endDate);

    /**
     * 나라로 조회
     * @param nation 나라
     * @return List<Object[]>
     */
    @Query("select m, d, p from Movie m left join Director d on m.id = d.movie_id left join Person p on d.person_id = p.id where m.nation = :nation")
    List<Object[]> getMoviesByNation(@Param("nation") String nation);

    /**
     * 평균 별점으로 조회
     * @param avgStar 평균 별점
     * @return List<Object[]>
     */
    @Query("select m, d, p from Movie m left join Director d on m.id = d.movie_id left join Person p on d.person_id = p.id where m.avgStar >= :avgStar")
    List<Object[]> getMoviesByAvgStar(@Param("avgStar") double avgStar);

    /**
     * 장르로 조회
     * @param genre 장르
     * @return List<Object[]>
     */
    @Query("select m, d, p from Movie m left join Director d on m.id = d.movie_id left join Person p on d.person_id = p.id where m.genre like %:genre%")
    List<Object[]> getMoviesByGenre(@Param("genre") String genre);

    /**
     * 나이제한으로 조회
     * @param ageLimit 나이제한
     * @return List<Object[]>
     */
    @Query("select m, d, p from Movie m left join Director d on m.id = d.movie_id left join Person p on d.person_id = p.id where m.ageLimit like %:ageLimit%")
    List<Object[]> getMoviesByAgeLimit(@Param("ageLimit") String ageLimit);

}
