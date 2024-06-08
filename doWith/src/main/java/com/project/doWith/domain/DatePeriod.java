package com.project.doWith.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "DATE")
public class DatePeriod {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DATE_ID")
    private Long date_id;

    @Column(name = "ACCOUNT",nullable = false)
    private ;

    @Column(name = "EMAIL",nullable = false,unique = true)
    private String email;

    @Column(name = "NAME",nullable = false)
    private String name;

    @Column(name = "PASSWORD",nullable = false)
    private String password;

    @Column(name = "PROFILE",nullable = true)
    private String profile;
}
