package com.y2h.tinybox.admin.event.repository;

import com.y2h.tinybox.admin.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface EventRepository extends JpaRepository<Event, Long> {
    @Override
    List<Event> findAll();
}
