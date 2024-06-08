package com.project.doWith.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "DATE_PERIOD")
public class DatePeriod {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DATE_ID")
    private Long date_id;

    @Column(name = "START_DATE",nullable = false)
    private Date start_date;

    @Column(name = "END_DATE",nullable = false,unique = true)
    private String end_date;

    @Column(name = "NAME",nullable = false)
    private Integer achieve_rate;

    @Column(name = "PASSWORD",nullable = false)
    private String password;

}
