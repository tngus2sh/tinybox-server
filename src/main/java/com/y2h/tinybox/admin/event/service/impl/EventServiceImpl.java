package com.y2h.tinybox.admin.event.service.impl;

import com.y2h.tinybox.admin.event.repository.EventRepository;
import com.y2h.tinybox.admin.event.service.dto.CreateEventDto;
import com.y2h.tinybox.admin.event.Event;
import com.y2h.tinybox.admin.event.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Override
    public Long createEvent(CreateEventDto dto) {
        Event event = Event.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .type(dto.getType())
                .winner(dto.getWinner())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .build();
        Event saveEvent = eventRepository.save(event);
        return saveEvent.getId();
    }
}
