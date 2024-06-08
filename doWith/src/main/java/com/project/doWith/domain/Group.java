package com.project.doWith.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "DW_GROUP")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GROUP_ID")
    private Long group_id;

    @Column(name = "GROUP_INTRO",nullable = true)
    private String group_intro;

    @Column(name = "GROUP_INFO",nullable = true)
    private String group_info;

    @Column(name = "GROUP_UUID")
    private UUID group_uuid;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Member_Group> userGroups = new HashSet<>();
}
