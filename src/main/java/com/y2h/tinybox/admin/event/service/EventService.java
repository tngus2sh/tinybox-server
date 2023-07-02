package com.y2h.tinybox.admin.event.service;

import com.y2h.tinybox.admin.event.Event;
import com.y2h.tinybox.admin.event.service.dto.CreateEventDto;
import com.y2h.tinybox.admin.event.service.dto.UpdateEventDto;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface EventService {
    Long createEvent(CreateEventDto dto);
    void updateEvent(UpdateEventDto dto);
    Event getEventById(Long id);
    List<Event> getEvents();
    void deleteEventById(Long id);
}
