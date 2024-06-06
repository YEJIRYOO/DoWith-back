package com.project.doWith.security.login;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.project.doWith.domain.Member;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@Builder
@AllArgsConstructor @NoArgsConstructor
public class Authority {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore //JSON 형식 직렬화 할 때 무시
    private Long id;

    private String name;

    @JoinColumn(name = "member")
    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Member member;


    public void setMember(Member member){
        this.member=member;
    }
}