package com.y2h.tinybox.client.api;

import com.y2h.tinybox.client.api.request.SignupMemberRequest;
import com.y2h.tinybox.client.member.service.MemberService;
import com.y2h.tinybox.client.member.service.dto.SignupMemberDto;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
@RequiredArgsConstructor
@Api(tags = {"회원계정"})
public class AccountApiController {

    private final MemberService memberService;

    @ApiOperation(value = "회원가입")
    @PostMapping("/signup")
    public Long signup(@RequestBody SignupMemberRequest request) {
        log.debug("SignupMemberRequest={}", request);
        SignupMemberDto dto = SignupMemberDto.builder()
                .loginId(request.getLoginId())
                .loginPw(request.getLoginPw())
                .name(request.getName())
                .tel(request.getTel())
                .email(request.getEmail())
                .birth(request.getBirth())
                .build();

        Long memberId = memberService.signup(dto);
        log.info("signup member={}", memberId);
        return memberId;
    }
}
