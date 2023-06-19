package com.y2h.tinybox.client.member.repository;

import com.y2h.tinybox.client.member.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {

    @Query("select m.id from Member m where m.loginId=:loginId")
    Optional<Long> existLoginId(@Param("loginId") String loginId);

    @Query("select m.id from Member m where m.tel=:tel")
    Optional<Long> existTel(@Param("tel") String tel);

    @Query("select m.id from Member m where m.email=:email")
    Optional<Long> existEmail(@Param("email") String email);
}
