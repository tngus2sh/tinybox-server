package com.y2h.tinybox.admin.event.service;

import com.y2h.tinybox.admin.event.Event;
import com.y2h.tinybox.admin.event.repository.EventRepository;
import com.y2h.tinybox.admin.event.service.dto.CreateEventDto;
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

        // then
        List<Event> all = eventRepository.findAll();
        assertThat(all.get(0).getId()).isEqualTo(eventId);

    }

}
