package com.y2h.tinybox.client.member.service;

import com.y2h.tinybox.client.member.Grade;
import com.y2h.tinybox.client.member.Member;
import com.y2h.tinybox.client.member.repository.MemberRepository;
import com.y2h.tinybox.client.member.service.dto.SignupMemberDto;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static com.y2h.tinybox.client.member.Grade.MEMBER;
import static com.y2h.tinybox.common.Active.ACTIVE;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
@SpringBootTest
@Transactional
class MemberServiceTest {

    @Autowired
    private MemberService memberService;
    @Autowired
    private MemberRepository memberRepository;

    @Test
    @DisplayName("회원가입#아이디 중복")
    void duplicationLoginId() {
        //given
        Member targetMember = insertMember();

        //when
        SignupMemberDto dto = SignupMemberDto.builder()
                .loginId("memberid")
                .build();

        //then
        assertThatThrownBy(() -> memberService.signup(dto))
                .isInstanceOf(DuplicateException.class);
    }

    @Test
    @DisplayName(" 회원가입#연락처 중복")
    void duplicationTel() {
        //given
        Member targetMember = insertMember();

        //when
        SignupMemberDto dto = SignupMemberDto.builder()
                .loginId("newMemberId")
                .tel("010-1234-5678")
                .build();

        //then
        assertThatThrownBy(() -> memberService.signup(dto))
                .isInstanceOf(DuplicateException.class);
    }

    @Test
    @DisplayName(" 회원가입#이메일 중복")
    void duplicationEmail() {
        //given
        Member targetMember = insertMember();

        //when
        SignupMemberDto dto = SignupMemberDto.builder()
                .loginId("newMemberId")
                .tel("010-1234-9876")
                .email("member@naver.com")
                .build();

        //then
        assertThatThrownBy(() -> memberService.signup(dto))
                .isInstanceOf(DuplicateException.class);
    }

    @Test
    @DisplayName(" 회원가입")
    void signup() {
        //given
        SignupMemberDto dto = SignupMemberDto.builder()
                .loginId("memberid")
                .loginPw("memberPw123!")
                .name("member")
                .tel("010-1234-5678")
                .email("member@naver.com")
                .birth("1990-12-30")
                .build();

        //when
        Long memberId = memberService.signup(dto);

        //then
        Optional<Member> findMember = memberRepository.findById(memberId);
        assertThat(findMember).isPresent();
    }

    private Member insertMember() {
        Member member = Member.builder()
                .loginId("memberid")
                .loginPw("memberPw123!")
                .name("member")
                .tel("010-1234-5678")
                .email("member@naver.com")
                .birth("1990-12-30")
                .grade(MEMBER)
                .active(ACTIVE)
                .build();
        return memberRepository.save(member);
    }

}