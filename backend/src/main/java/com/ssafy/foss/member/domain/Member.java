package com.ssafy.foss.member.domain;

import com.ssafy.foss.member.dto.UpdateMemberRequest;
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
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String profileImg;

    private String socialId;

    @Enumerated(EnumType.STRING)
    private Role role;

    public void change(UpdateMemberRequest updateMemberRequest) {
        this.name = updateMemberRequest.getName();
        this.email = updateMemberRequest.getEmail();
    }
}
