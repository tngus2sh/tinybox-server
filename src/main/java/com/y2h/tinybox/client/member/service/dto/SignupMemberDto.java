package com.y2h.tinybox.client.member.service.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class SignupMemberDto {

    private String loginId;
    private String loginPw;
    private String name;
    private String tel;
    private String email;
    private String birth;

    @Builder
    public SignupMemberDto(String loginId, String loginPw, String name, String tel, String email, String birth) {
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.name = name;
        this.tel = tel;
        this.email = email;
        this.birth = birth;
    }
}
