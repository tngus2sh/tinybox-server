package com.y2h.tinybox.movie.repository;

import com.y2h.tinybox.movie.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MovieRepository extends JpaRepository<Movie, Long> {


}
