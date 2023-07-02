package com.y2h.tinybox.client.movie.repository;

import com.y2h.tinybox.client.movie.Director;
import com.y2h.tinybox.client.movie.Movie;
import com.y2h.tinybox.client.movie.service.dto.PersonDirectorDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.security.core.parameters.P;

import java.util.List;

public interface DirectorRepository extends JpaRepository<Director, Long> {
    @Query("select d from Director d where d.movie=:movie")
    List<Director> getDirectors(@Param("movie") Movie movie);

    /**
     * movieId에 맞는 영화 배우, 감독 리스트 출력
     * @param movie 영화
     * @return List<Object[]>
     */
    @Query("select d,p from Director d left join d.person p where d.movie=:movie")
    List<Object[]> getPersonDirectors(@Param("movie") Movie movie);
}
