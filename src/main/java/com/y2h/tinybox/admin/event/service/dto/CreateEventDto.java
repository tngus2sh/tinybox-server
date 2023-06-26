package com.y2h.tinybox.admin.event.service.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

import java.util.Date;

@Data
@Getter
public class CreateEventDto {
    private String title;
    private String content;
    private String uploadFileName;
    private String storeFileName;
    private String type;
    private int winner;
    private Date startDate;
    private Date endDate;

    @Builder
    public CreateEventDto(String title, String content, String uploadFileName, String storeFileName, String type, int winner, Date startDate, Date endDate) {
        this.title = title;
        this.content = content;
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
        this.type = type;
        this.winner = winner;
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
