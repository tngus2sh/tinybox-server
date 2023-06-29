package com.y2h.tinybox.admin.event;

import com.y2h.tinybox.client.member.Member;
import com.y2h.tinybox.common.TimeBaseEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@ToString
public class Event extends TimeBaseEntity {
    @Id
    @GeneratedValue
    @Column(name = "event_id")
    Long id;

    @Setter
    @Column(nullable = false, length = 30)
    private String title;

    @Setter
    @Column(nullable = false)
    private String content;

    @Setter
    @Column(length = 60)
    private String uploadFileName;

    @Setter
    @Column(length = 60)
    private String storeFileName;

    @Column(nullable = false, updatable = false, length = 30)
    private String type;

    @Setter
    @Column(nullable = false)
    private int winner;

    @Column(nullable = false, updatable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date startDate;

    @Column(nullable = false, updatable = false)
    @Temporal(value = TemporalType.TIMESTAMP)
    private Date endDate;

    @Setter
    @Column(nullable = false)
    private int hit = 0;


    public Event() {}


    @Builder
    public Event(Long id, String title, String content, String uploadFileName, String storeFileName, String type, int winner, Date startDate, Date endDate, int hit) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
        this.type = type;
        this.winner = winner;
        this.startDate = startDate;
        this.endDate = endDate;
        this.hit = hit;
    }


    public boolean isClosed(long cnt) {
        return cnt >= winner;
    }

//    public boolean isClosed(long cnt) {
//        return cnt == 0;
//    }
}
