package com.y2h.tinybox.admin.api;

import com.y2h.tinybox.admin.api.request.CreateEventRequest;
import com.y2h.tinybox.admin.api.request.UpdateEventRequest;
import com.y2h.tinybox.admin.event.Event;
import com.y2h.tinybox.admin.event.service.EventService;
import com.y2h.tinybox.admin.event.service.NoContentException;
import com.y2h.tinybox.admin.event.service.dto.CreateEventDto;
import com.y2h.tinybox.admin.event.service.dto.UpdateEventDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@Slf4j
@RequiredArgsConstructor
@Api(tags = {"이벤트"})
@RequestMapping("/event")
public class EventApiController {

    private final EventService eventService;


    @ApiOperation(value = "이벤트 등록")
    @PostMapping("")
    public Long createEvent(@RequestBody CreateEventRequest request) throws ParseException {
        log.debug("CreateEventRequest={}", request);

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate = format.parse(request.getStartDate());
        Date endDate = format.parse(request.getEndDate());

        CreateEventDto dto = CreateEventDto.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .uploadFileName(request.getUploadFileName())
                .storeFileName(request.getStoreFileName())
                .type(request.getType())
                .winner(request.getWinner())
                .startDate(startDate)
                .endDate(endDate)
                .build();

        Long eventId = eventService.createEvent(dto);
        log.info("create event={}", eventId);
        return eventId;
    }


    @ApiOperation(value = "이벤트 상세 조회")
    @GetMapping("/{id}")
    private ResponseEntity<?> viewEvent(@PathVariable("id") Long id) {
        log.debug("getEventById={}", id);

        try {
            Event getEvent = eventService.getEventById(id);
            return new ResponseEntity<Event>(getEvent, HttpStatus.OK);
        }
        catch(NoContentException e) {
            return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @ApiOperation(value = "이벤트 전체 조회") // 스크롤 다운 하면 새로운 목록 n개씩 출력되게 바꿀 예정
    @GetMapping("")
    private ResponseEntity<?> listEvent() {
        log.debug("getEventList");

        try {
            List<Event> list = eventService.getEvents();
            return new ResponseEntity<List<Event>>(list, HttpStatus.OK);
        }
        catch(Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @ApiOperation(value = "이벤트 수정")
    @PostMapping("/change")
    private ResponseEntity<?> updateEvent(@RequestBody UpdateEventRequest request) {
        log.debug("UpdateEventRequest={}", request);

        UpdateEventDto dto = UpdateEventDto.builder()
                .id(request.getId())
                .title(request.getTitle())
                .content(request.getContent())
                .uploadFileName(request.getUploadFileName())
                .storeFileName(request.getStoreFileName())
                .winner(request.getWinner())
                .build();

        try {
            eventService.updateEvent(dto);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @ApiOperation(value = "이벤트 삭제")
    @GetMapping("/{id}")
    private ResponseEntity<?> deleteEvent(@PathVariable("id") Long id) {
        log.debug("DeleteEventById={}", id);

        try {
            eventService.deleteEventById(id);
            return new ResponseEntity<Void>(HttpStatus.OK);
        }
        catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<Void>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
