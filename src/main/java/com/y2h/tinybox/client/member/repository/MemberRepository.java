package com.y2h.tinybox.client.member.repository;

import com.y2h.tinybox.client.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
