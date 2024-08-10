package com.project.doWith.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "DW_GROUP")
public class Groups {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GROUP_ID")
    private Long group_id;

    @Column(name = "GROUP_TITLE",nullable = true)
    private String group_title; //그룹명 추가

    @Column(name = "GROUP_INTRO",nullable = true)
    private String group_intro;

    @Column(name = "GROUP_INFO",nullable = true)
    private String group_info;

    @Column(name = "GROUP_UUID",nullable = true)
    private String groupUuid;

    @OneToMany(mappedBy = "group",cascade = CascadeType.ALL)
    List<DatePeriod> datePeriods;

    @Builder. Default
    @OneToMany(mappedBy = "groups", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Member_Group> userGroups = new HashSet<>();
    //upserGroups는 HashSet<>()을 기본 값으로 가짐
    //-> builder default 설정 필요
}
