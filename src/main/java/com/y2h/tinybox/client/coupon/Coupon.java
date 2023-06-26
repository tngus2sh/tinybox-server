package com.y2h.tinybox.client.coupon;

import com.y2h.tinybox.admin.event.Event;
import com.y2h.tinybox.client.member.Member;
import com.y2h.tinybox.common.TimeBaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Getter
@ToString
public class Coupon extends TimeBaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "coupon_id")
    Long id;

    @JoinColumn(name = "event_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Event event;

    @JoinColumn(name = "member_id", nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Member member;


    public Coupon() {}


    @Builder
    public Coupon(Long id, Event event, Member member) {
        this.id = id;
        this.event = event;
        this.member = member;
    }
}
