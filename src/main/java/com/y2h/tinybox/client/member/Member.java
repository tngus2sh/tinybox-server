package com.y2h.tinybox.client.member;

import com.y2h.tinybox.common.Active;
import com.y2h.tinybox.common.TimeBaseEntity;
import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static com.y2h.tinybox.client.member.Grade.MEMBER;
import static com.y2h.tinybox.common.Active.ACTIVE;
import static javax.persistence.EnumType.STRING;

@Entity
@Getter
public class Member extends TimeBaseEntity implements UserDetails {

    @Id
    @GeneratedValue
    @Column(name = "member_id")
    Long id;
    @Column(unique = true, nullable = false, updatable = false, length = 20)
    private String loginId;
    @Column(nullable = false, length = 20)
    private String loginPw;
    @Column(nullable = false, updatable = false, length = 50)
    private String name;
    @Column(unique = true, nullable = false, length = 13)
    private String tel;
    @Column(unique = true, nullable = false, length = 100)
    private String email;
    @Column(nullable = false, length = 10)
    private String birth;
    @Enumerated(STRING)
    @Column(nullable = false, length = 20)
    private Grade grade;
    @Enumerated(STRING)
    @Column(nullable = false, length = 20)
    private Active active;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<String> roles = new ArrayList<>();

    public Member() {
    }

    @Builder
    public Member(Long id, String loginId, String loginPw, String name, String tel, String email, String birth, Grade grade, Active active, List<String> roles) {
        this.id = id;
        this.loginId = loginId;
        this.loginPw = loginPw;
        this.name = name;
        this.tel = tel;
        this.email = email;
        this.birth = birth;
        this.grade = grade;
        this.active = active;
        this.roles = roles;
    }

    /*
    연관관계 편의 메서드
     */
    public static Member createMember(String loginId, String loginPw, String name, String tel, String email, String birth) {
        return Member.builder()
                .loginId(loginId)
                .loginPw(loginPw)
                .name(name)
                .tel(tel)
                .email(email)
                .birth(birth)
                .grade(MEMBER)
                .active(ACTIVE)
                .roles(Collections.singletonList("MEMBER"))
                .build();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles.stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return loginPw;
    }

    @Override
    public String getUsername() {
        return loginId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
