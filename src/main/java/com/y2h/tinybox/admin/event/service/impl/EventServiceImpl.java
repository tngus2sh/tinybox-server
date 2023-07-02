package com.y2h.tinybox.admin.event.service.impl;

import com.y2h.tinybox.admin.event.repository.EventRepository;
import com.y2h.tinybox.admin.event.service.NoContentException;
import com.y2h.tinybox.admin.event.service.dto.CreateEventDto;
import com.y2h.tinybox.admin.event.Event;
import com.y2h.tinybox.admin.event.service.EventService;
import com.y2h.tinybox.admin.event.service.dto.UpdateEventDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    @Override
    public Long createEvent(CreateEventDto dto) {
        Event event = Event.builder()
                .title(dto.getTitle())
                .content(dto.getContent())
                .uploadFileName(dto.getUploadFileName())
                .storeFileName(dto.getStoreFileName())
                .type(dto.getType())
                .winner(dto.getWinner())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .build();
        Event saveEvent = eventRepository.save(event);
        return saveEvent.getId();
    }

    @Override
    @Transactional // 사용하는 이유? (save로 업데이트 할 때는 사용 안 함)
    public void updateEvent(UpdateEventDto dto) {
        Optional<Event> findEvent = eventRepository.findById(dto.getId());

        if(findEvent.isPresent()) {
            Event event = findEvent.get();
            event.setTitle(dto.getTitle());
            event.setContent(dto.getContent());
            event.setUploadFileName(dto.getUploadFileName());
            event.setStoreFileName(dto.getStoreFileName());
            event.setWinner(dto.getWinner());
        }
    }

    @Override
    public Event getEventById(Long id) {
        Optional<Event> findEvent = eventRepository.findById(id);

        if(findEvent.isPresent()) {
            Event event = findEvent.get();
            return event;
        }

        throw new NoContentException();
    }

    @Override
    public List<Event> getEvents() {
        List<Event> events = eventRepository.findAll();
        return events;
    }

    @Override
    public void deleteEventById(Long id) {
        eventRepository.deleteById(id);
    }
}
