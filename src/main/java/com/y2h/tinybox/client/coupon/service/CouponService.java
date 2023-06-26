package com.y2h.tinybox.client.coupon.service;

import com.y2h.tinybox.client.coupon.service.dto.CreateCouponDto;
import org.springframework.transaction.annotation.Transactional;

//@Transactional
public interface CouponService {
    Long createCoupon(CreateCouponDto dto);
//    Long createConcurrentCoupon(long id);
//    void setUsableCoupon(long id, int quantity);
//    int usableCoupon(long id);
}
