package com.y2h.tinybox.client.coupon.service.dto;

import com.y2h.tinybox.admin.event.Event;
import com.y2h.tinybox.client.member.Member;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class CreateCouponDto {
    private Event event;
    private Member member;

    @Builder
    public CreateCouponDto(Event event, Member member) {
        this.event = event;
        this.member = member;
    }
}
