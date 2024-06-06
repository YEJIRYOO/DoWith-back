package com.project.doWith.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "GROUP")
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
}
