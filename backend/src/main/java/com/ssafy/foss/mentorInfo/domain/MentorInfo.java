package com.ssafy.foss.mentorInfo.domain;

import com.ssafy.foss.company.domain.Company;
import com.ssafy.foss.member.domain.Member;
import com.ssafy.foss.mentorInfo.dto.UpdateMentorInfoRequest;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class MentorInfo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    private String selfProduce;

    public void change(UpdateMentorInfoRequest updateMentorInfoRequest) {
        this.selfProduce = updateMentorInfoRequest.getSelfProduce();
    }
}
