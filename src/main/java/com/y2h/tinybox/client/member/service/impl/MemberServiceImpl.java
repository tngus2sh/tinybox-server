package com.y2h.tinybox.client.member.service.impl;

import com.y2h.tinybox.client.member.Member;
import com.y2h.tinybox.client.member.repository.MemberRepository;
import com.y2h.tinybox.client.member.service.DuplicateException;
import com.y2h.tinybox.client.member.service.MemberService;
import com.y2h.tinybox.client.member.service.dto.SignupMemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;

    @Override
    public Long signup(SignupMemberDto dto) {
        Optional<Long> loginId = memberRepository.existLoginId(dto.getLoginId());
        if (loginId.isPresent()) {
            throw new DuplicateException();
        }

        Optional<Long> tel = memberRepository.existTel(dto.getTel());
        if (tel.isPresent()) {
            throw new DuplicateException();
        }

        Optional<Long> email = memberRepository.existEmail(dto.getEmail());
        if (email.isPresent()) {
            throw new DuplicateException();
        }

        Member member = Member.createMember(dto.getLoginId(), dto.getLoginPw(), dto.getName(), dto.getTel(), dto.getEmail(), dto.getBirth());
        Member savedMember = memberRepository.save(member);
        return savedMember.getId();
    }
}
