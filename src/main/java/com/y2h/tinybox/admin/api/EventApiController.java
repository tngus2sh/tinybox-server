package com.y2h.tinybox.admin.api;

import com.y2h.tinybox.admin.api.request.CreateEventRequest;
import com.y2h.tinybox.admin.event.service.EventService;
import com.y2h.tinybox.admin.event.service.dto.CreateEventDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@Slf4j
@RequiredArgsConstructor
@Api(tags = {"이벤트"})
public class EventApiController {

    private final EventService eventService;

    @ApiOperation(value = "이벤트 등록")
    @PostMapping("/event")
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


//    @ApiOperation(value = "이벤트 상세 조회")
//    @GetMapping("/event")
//    public
}
