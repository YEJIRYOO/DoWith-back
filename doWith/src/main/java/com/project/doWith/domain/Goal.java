package com.project.doWith.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "GOAL")
public class Goal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "GOAL_ID")
    private Long goal_id;

    @Column(name = "RANGE")
    private Integer range;
    //공통->0, 개인->1

    @Column(name = "CONFIRM_NAME")
    private String confirm_name;

    @Column(name = "CONFIRM_IMG")
    private String confirm_img;

    @Column(name = "CONFIRM_REVIEW")
    private String confirm_review;

    @Column(name = "COMPLETE")
    private Boolean complete;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "date_id")
    private DatePeriod datePeriod;

}
