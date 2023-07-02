package com.y2h.tinybox.client.movie.repository;

import com.y2h.tinybox.client.movie.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
