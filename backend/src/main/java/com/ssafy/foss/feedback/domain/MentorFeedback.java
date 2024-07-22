package com.ssafy.foss.feedback.domain;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Entity
@Getter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MentorFeedback {

    @EmbeddedId
    private MentorFeedbackId id;

    @Column(name = "good_point")
    private String goodPoint;

    @Column(name = "bad_point")
    private String badPoint;

    @Column(name = "summary")
    private String summary;
}

@Embeddable
@Getter
@ToString
@AllArgsConstructor
@NoArgsConstructor
class MentorFeedbackId implements Serializable {
    @Column(name = "schedule_id")
    private Long scheduleId;

    @Column(name = "member_id")
    private Long memberId;
}
