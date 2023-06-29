package com.y2h.tinybox.admin.event.service;

import com.y2h.tinybox.admin.event.Event;
import com.y2h.tinybox.admin.event.repository.EventRepository;
import com.y2h.tinybox.admin.event.service.dto.CreateEventDto;
import com.y2h.tinybox.admin.event.service.dto.UpdateEventDto;
import com.y2h.tinybox.client.member.Member;
import com.y2h.tinybox.client.member.repository.MemberRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.y2h.tinybox.client.member.Grade.MEMBER;
import static com.y2h.tinybox.common.Active.ACTIVE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
public class EventServiceTest {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private EventService eventService;
    @Autowired
    private EventRepository eventRepository;

    @Test
    @DisplayName(" 이벤트 등록")
    void createEvent() throws ParseException {
        //given
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate = format.parse("2023-06-27 00:00:00");
        Date endDate = format.parse("2023-06-27 12:59:59");
        CreateEventDto dto = CreateEventDto.builder()
                .title("이벤트")
                .content("새로운 이벤트가 시작되었습니다.")
                .type("일반")
                .winner(50)
                .startDate(startDate)
                .endDate(endDate)
                .build();

        //when
        Long eventId = eventService.createEvent(dto);

        //then
        Optional<Event> findEvent = eventRepository.findById(eventId);
        assertThat(findEvent).isPresent();
    }

    @Test
    @DisplayName(" 이벤트 수정")
    void updateEvent() throws ParseException {
        //given
        Event targetEvent = insertEvent();
        Long targetEventId = targetEvent.getId();

        //when
        UpdateEventDto dto = UpdateEventDto.builder()
                .id(targetEventId)
                .title("이벤트 수정")
                .content("이벤트 수정입니다.")
                .winner(70)
                .build();

        eventService.updateEvent(dto);

        //then
        Event findEvent = eventRepository.findById(targetEventId).get();
        assertThat(findEvent.getTitle()).isEqualTo("이벤트 수정");
    }

    @Test
    @DisplayName(" 이벤트 조회")
    void selectEvent() throws ParseException {
        //given
        Event targetEvent = insertEvent();
        Long targetEventId = targetEvent.getId();

        //when
        Optional<Event> findEvent = eventRepository.findById(targetEventId);

        //then
        assertThat(findEvent).isPresent();
    }

    @Test
    @DisplayName(" 이벤트 삭제")
    void deleteEvent() throws ParseException {
        //given
        Event targetEvent = insertEvent();
        Long targetEventId = targetEvent.getId();

        //when
        eventRepository.deleteById(targetEventId);

        //then
        Optional<Event> afterDeleteEvent = eventRepository.findById(targetEventId);
        assertThat(afterDeleteEvent).isEmpty();
    }


    private Event insertEvent() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate = format.parse("2023-06-27 00:00:00");
        Date endDate = format.parse("2023-06-27 12:59:59");
        Event dto = Event.builder()
                .title("이벤트")
                .content("새로운 이벤트가 시작되었습니다.")
                .type("일반")
                .winner(50)
                .startDate(startDate)
                .endDate(endDate)
                .build();

        return eventRepository.save(dto);
    }

}
