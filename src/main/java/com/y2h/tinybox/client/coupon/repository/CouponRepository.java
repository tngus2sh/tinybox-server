package com.y2h.tinybox.client.coupon.repository;

import com.y2h.tinybox.admin.event.Event;
import com.y2h.tinybox.client.coupon.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CouponRepository extends JpaRepository<Coupon, Long> {

    @Query("select count(c) from Coupon c where c.event=:event")
    long countExistCoupon(@Param("event") Event event);
}
