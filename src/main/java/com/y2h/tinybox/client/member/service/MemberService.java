package com.y2h.tinybox.client.member.service;

import com.y2h.tinybox.client.member.service.dto.SignupMemberDto;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public interface MemberService {

    Long signup(SignupMemberDto dto);
}
