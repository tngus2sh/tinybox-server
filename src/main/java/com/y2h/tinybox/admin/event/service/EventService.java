package com.y2h.tinybox.admin.event.service;

import com.y2h.tinybox.admin.event.service.dto.CreateEventDto;
import com.y2h.tinybox.admin.event.service.dto.UpdateEventDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface EventService {
    public Long createEvent(CreateEventDto dto);
    public void updateEvent(UpdateEventDto dto);
}
