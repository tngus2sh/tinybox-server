package com.y2h.tinybox.admin.api.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class UpdateEventRequest {

    @ApiModelProperty(example = "11")
    private Long id;

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

    @Range(min = 1)
    @ApiModelProperty(example = "50")
    private int winner;
}
