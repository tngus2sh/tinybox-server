package com.y2h.tinybox.admin.event.service.dto;

import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
public class UpdateEventDto {
    private Long id;
    private String title;
    private String content;
    private String uploadFileName;
    private String storeFileName;
    private int winner;

    @Builder
    public UpdateEventDto(Long id, String title, String content, String uploadFileName, String storeFileName, int winner) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
        this.winner = winner;
    }


}
