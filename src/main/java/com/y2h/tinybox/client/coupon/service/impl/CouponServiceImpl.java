package com.y2h.tinybox.client.coupon.service.impl;

import com.y2h.tinybox.admin.event.Event;
import com.y2h.tinybox.admin.event.repository.EventRepository;
import com.y2h.tinybox.client.coupon.Coupon;
import com.y2h.tinybox.client.coupon.repository.CouponRepository;
import com.y2h.tinybox.client.coupon.service.CouponService;
import com.y2h.tinybox.client.coupon.service.dto.CreateCouponDto;
import com.y2h.tinybox.client.member.Member;
import com.y2h.tinybox.client.member.repository.MemberRepository;
import com.y2h.tinybox.client.member.service.DuplicateException;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {

    private final MemberRepository memberRepository;
    private final EventRepository eventRepository;
    private final CouponRepository couponRepository;
    private final RedissonClient redissonClient;

    @Override
    @Transactional
    public Long createCoupon(CreateCouponDto dto) {
        RLock lock = redissonClient.getLock(String.valueOf(dto.getEvent().getId()));
        String threadName = Thread.currentThread().getName();

        try {
            if(!lock.tryLock(10, 3, TimeUnit.SECONDS)) {
                System.out.println("lock 획득 실패: " + threadName);
                return 0l;
//                throw new RuntimeException("lock 획득 실패");
            }

            System.out.println("lock 획득: " + threadName);
            System.out.println("이벤트 검색: " + dto.getEvent().getId());
            Optional<Event> findEvent = eventRepository.findById(dto.getEvent().getId());
            if(findEvent.isPresent()) {
                System.out.println("찾은 이벤트: " + findEvent.get());
            }
            else {
                System.out.println("이벤트 존재하지 않음");
                return 0l;
            }
            Optional<Member> findMember = memberRepository.findById(dto.getMember().getId());
            if(findMember.isPresent()) {
                System.out.println("찾은 멤버: " + findMember.get());
            }
            else {
                System.out.println("멤버 존재하지 않음");
                return 0l;
            }
            Event event = findEvent.get();
            Member member = findMember.get();

            System.out.println(event);
            System.out.println(member);

            // business logic
            long cnt = couponRepository.countExistCoupon(event);
            System.out.println("저장된 쿠폰: " + cnt);
            if(event.isClosed(cnt)) {
                System.out.println("마감되었습니다.");
                return 0l;
//                throw new DuplicateException("마감되었습니다.");
            }

            Coupon coupon = Coupon.builder()
                    .event(event)
                    .member(member)
                    .build();
            System.out.println(coupon);

            Coupon saveCoupon = couponRepository.saveAndFlush(coupon);
            System.out.println((cnt+1) + "번째 쿠폰 발급 완료");
            return saveCoupon.getId();

        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            if(lock != null && lock.isLocked() && lock.isHeldByCurrentThread()) {
//            if(lock != null && lock.isLocked()) {
                lock.unlock();
                System.out.println("lock 반납: " + threadName);
            }
//            lock.unlock();
//            System.out.println("lock 반납");
        }

    }

//    @Override
//    public Long createConcurrentCoupon(long id) {
//        String lockName = String.valueOf(id) + ":lock";
//        RLock lock = redissonClient.getLock(lockName);
//        String threadName = Thread.currentThread().getName();
//
//        try {
//            if(!lock.tryLock(5, 3, TimeUnit.SECONDS)) {
//                System.out.println("lock 획득 실패: " + threadName);
//                return 0l;
//            }
//
//            Optional<Event> findEvent = eventRepository.findById(id);
//            if(findEvent.isPresent()) {
//                System.out.println("찾은 이벤트: " + findEvent.get());
//            }
//            else {
//                System.out.println("이벤트 없음");
//                return 0l;
//            }
//
//            Event event = findEvent.get();
//
//            int quantity = usableCoupon(id);
//            if(event.isClosed(quantity)) {
//                System.out.println("마감되었습니다.");
//                return 0l;
////                throw new DuplicateException("마감되었습니다.");
//            }
//
//
//
//
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//    }

//    @Override
//    public void setUsableCoupon(long id, int quantity) {
//        redissonClient.getBucket(String.valueOf(id)).set(quantity);
//    }
//
//    @Override
//    public int usableCoupon(long id) {
//        return (int) redissonClient.getBucket(String.valueOf(id)).get();
//    }
}
