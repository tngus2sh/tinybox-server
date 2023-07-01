package com.y2h.tinybox.admin.api.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class CreateEventRequest {

    @NotBlank
    @Size(max = 30)
    @Pattern(regexp = "^[a-zA-Z0-9가-힣.,!?]*$")
    @ApiModelProperty(example = "신작 개봉 이벤트")
    private String title;

    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9가-힣.,!?]*$")
    @ApiModelProperty(example = "신작 개봉 기념 관람권 50장 증정합니다.")
    private String content;

    @Pattern(regexp = "^[a-zA-Z0-9가-힣]*.(jpg|jpeg|png|gif|bmp)$")
    @ApiModelProperty(example = "test01.jpg")
    private String uploadFileName;

    @Pattern(regexp = "^[a-zA-Z0-9가-힣]*.(jpg|jpeg|png|gif|bmp)$")
    @ApiModelProperty(example = "test01.jpg")
    private String storeFileName;

    @NotBlank
    @Pattern(regexp = "(유형1|유형2|유형3)")
    @ApiModelProperty(example = "유형1")
    private String type;

    @Range(min = 1)
    @ApiModelProperty(example = "50")
    private int winner;

    @NotBlank
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")
    @ApiModelProperty(example = "2023-06-27 00:00:00")
    private String startDate;

    @NotBlank
    @Pattern(regexp = "\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}")
    @ApiModelProperty(example = "2023-06-27 00:00:00")
    private String endDate;
}
