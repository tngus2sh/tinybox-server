package com.y2h.tinybox.client.coupon.service;

import com.y2h.tinybox.admin.event.Event;
import com.y2h.tinybox.admin.event.repository.EventRepository;
import com.y2h.tinybox.admin.event.service.EventService;
import com.y2h.tinybox.admin.event.service.dto.CreateEventDto;
import com.y2h.tinybox.client.coupon.repository.CouponRepository;
import com.y2h.tinybox.client.coupon.service.dto.CreateCouponDto;
import com.y2h.tinybox.client.member.Member;
import com.y2h.tinybox.client.member.repository.MemberRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.scheduling.annotation.Async;

import javax.transaction.Transactional;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

import static com.y2h.tinybox.client.member.Grade.MEMBER;
import static com.y2h.tinybox.common.Active.ACTIVE;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@Transactional
public class CouponServiceTest {

    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private EventRepository eventRepository;

    @Autowired
    private CouponRepository couponRepository;
    @Autowired
    private CouponService couponService;

    private Event event;
    private List<Member> memberList = new ArrayList<>();
    private int cnt = 0;

    private class CreateMemberCoupon implements Runnable {

        private final Event saveEvent;
        private final Member saveMember;
//        private final long id;
        private final CountDownLatch latch;

        public CreateMemberCoupon(int i, CountDownLatch latch) {
            this.saveEvent = event;
            this.saveMember = memberList.get(i);
            this.latch = latch;
        }

//        public CreateMemberCoupon(long id, CountDownLatch latch) {
//            this.id = id;
//            this.latch = latch;
//        }

        @Override
        public void run() {
//            couponService.createConcurrentCoupon(id);
            CreateCouponDto dto = CreateCouponDto.builder()
                    .event(saveEvent)
                    .member(saveMember)
                    .build();
            couponService.createCoupon(dto);
            latch.countDown();
        }
    }

//    @BeforeEach
//    void createEventMember() throws ParseException {
//        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//        Date startDate = format.parse("2023-06-27 00:00:00");
//        Date endDate = format.parse("2023-06-27 12:59:59");
//        Event event = Event.builder()
//                .title("이벤트")
//                .content("새로운 이벤트가 시작되었습니다.")
//                .type("일반")
//                .winner(20)
//                .startDate(startDate)
//                .endDate(endDate)
//                .build();
//        this.event = eventRepository.save(event);
////        couponService.setUsableCoupon(this.event.getId(), 20);
//        System.out.println("이벤트 저장 완료");
//
//        for(int i = 0; i < 50; i++) {
//            memberList.add(insertMember(i));
//        }
//    }

//    @BeforeEach
//    void createMember() {
//        for(int i = 0; i < 50; i++) {
//            memberList.add(insertMember(i));
//        }
//    }

    @Test
    @DisplayName(" 쿠폰 등록")
    void createCoupon() throws ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate = format.parse("2023-06-27 00:00:00");
        Date endDate = format.parse("2023-06-27 12:59:59");
        Event event = Event.builder()
                .title("이벤트")
                .content("새로운 이벤트가 시작되었습니다.")
                .type("일반")
                .winner(20)
                .startDate(startDate)
                .endDate(endDate)
                .build();
        this.event = eventRepository.save(event);
        System.out.println("이벤트 저장 완료: " + this.event);

        for(int i = 0; i < 50; i++) {
            memberList.add(insertMember(i));
        }

        CreateCouponDto dto = CreateCouponDto.builder()
                .event(this.event)
                .member(memberList.get(0))
                .build();
        couponService.createCoupon(dto);
    }

    @Async
    void startConcurrent(int i, CountDownLatch latch) {
        CreateCouponDto dto = CreateCouponDto.builder()
                .event(event)
                .member(memberList.get(i))
                .build();
        couponService.createCoupon(dto);
        latch.countDown();
    }

    @Test
    @DisplayName(" 쿠폰 동시성 등록")
    void createConcurrentCoupon() throws InterruptedException, ParseException {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date startDate = format.parse("2023-06-27 00:00:00");
        Date endDate = format.parse("2023-06-27 12:59:59");
        Event event = Event.builder()
                .title("이벤트")
                .content("새로운 이벤트가 시작되었습니다.")
                .type("일반")
                .winner(20)
                .startDate(startDate)
                .endDate(endDate)
                .build();
        this.event = eventRepository.saveAndFlush(event);
        System.out.println("이벤트 저장 완료: " + this.event);

        for(int i = 0; i < 50; i++) {
            memberList.add(insertMember(i));
        }

        System.out.println("동시성 등록 시작");

//        Optional<Event> findEvent = eventRepository.findById(this.event.getId());
//        if(findEvent.isPresent()) {
//            System.out.println("저장된 이벤트: " + findEvent.get());
//        }
//        else {
//            System.out.println("이벤트 없음!!!!!!!!!!");
//            return;
//        }
//        this.event = findEvent.get();


//        int numberOfThreads = 50;
//        CountDownLatch latch = new CountDownLatch(numberOfThreads);
//
//        for(int i = 0; i < numberOfThreads; i++) {
//            startConcurrent(i, latch);
//        }
//
//        latch.await();


//        int numberOfThreads = 50;
//        CountDownLatch latch = new CountDownLatch(numberOfThreads);
//        List<Thread> threadList = new ArrayList<>();
//
//        for(int i = 0; i < numberOfThreads; i++) {
//            threadList.add(new Thread(new CreateMemberCoupon(i, latch)));
//        }
//
////        threadList.forEach(Thread::start);
//        for(Thread t: threadList) {
//            t.start();
//        }
//        latch.await();


        int numberOfThreads = 50;
        CountDownLatch latch = new CountDownLatch(numberOfThreads);
        ExecutorService executorService = Executors.newFixedThreadPool(numberOfThreads);

        for(int i = 0; i < numberOfThreads; i++) {
            Member member = memberList.get(i);
            executorService.execute(() -> {
                CreateCouponDto dto = CreateCouponDto.builder()
                        .event(this.event)
                        .member(member)
                        .build();

                Long id = couponService.createCoupon(dto);
//                cmc.startTest(saveEvent, member);
                latch.countDown();
            });
        }

        latch.await();

//        long total = couponRepository.count();
//        assertThat(total == 20);

    }


    private Member insertMember(int i) {
        Member member = Member.builder()
                .loginId("member" + i)
                .loginPw("memberPw123!")
                .name("member")
                .tel("010-1234-5678")
                .email("member" + i + "@naver.com")
                .birth("1990-12-30")
                .grade(MEMBER)
                .active(ACTIVE)
                .build();
        System.out.println(member);
        return memberRepository.saveAndFlush(member);
    }

}
