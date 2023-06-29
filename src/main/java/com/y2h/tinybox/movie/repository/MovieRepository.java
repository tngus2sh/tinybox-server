package com.y2h.tinybox.movie.repository;

import com.y2h.tinybox.movie.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MovieRepository extends JpaRepository<Movie, Long> {

    /*
        영화 주제로 검색
     */
    @Query("select m, d, p from Movie m left join Director d on m.id = d.movie_id left join Person p on d.person_id = p.id where m.koreanTitle like %:title% or m.englishTitle like %:title%")
    List<Object[]> getMoviesByTitle(@Param("title") String title);

    /*
        사람(배우, 감독)으로 검색
     */
    @Query("select m, d, p from Movie m left join Director d on m.id = d.movie_id left join Person p on d.person_id = p.id where p.name = :person")
    List<Object[]> getMoviesByPerson(@Param("person") String person);

}
