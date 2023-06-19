package com.y2h.tinybox.client.api.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class SignupMemberRequest {

    @NotBlank
    @Size(max = 20)
    @Pattern(regexp = "^[a-zA-Z0-9]*$")
    @ApiModelProperty(example = "memberid")
    private String loginId;
    @NotBlank
    @Size(min = 8, max = 20)
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)(?=.*[@$!%*#?&])[A-Za-z\\d@$!%*#?&]{8,}$")
    @ApiModelProperty(example = "harry1234!")
    private String loginPw;
    @NotBlank
    @Size(max = 50)
    @Pattern(regexp = "^[a-zA-Z가-힣]*$")
    @ApiModelProperty(example = "harry potter")
    private String name;
    @NotBlank
    @Size(min = 13, max = 13)
    @Pattern(regexp = "^\\d{3}-\\d{3,4}-\\d{4}$")
    @ApiModelProperty(example = "010-1234-1234")
    private String tel;
    @NotBlank
    @Size(max = 100)
    @Email
    @ApiModelProperty(example = "harry@gmail.com")
    private String email;
    @NotBlank
    @Size(min = 10, max = 10)
    @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$")
    @ApiModelProperty(example = "1990-01-10")
    private String birth;


    @NotBlank
    @ApiModelProperty(example = "MEMBER")
    private String grade;
}
