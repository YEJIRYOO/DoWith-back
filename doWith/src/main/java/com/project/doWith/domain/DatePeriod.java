package com.project.doWith.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

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
    private LocalDate start_date;

    @Column(name = "END_DATE",nullable = false,unique = true)
    private LocalDate end_date;

    @Column(name = "NAME",nullable = false)
    private Integer achieve_rate;

    @OneToMany(mappedBy = "datePeriod", cascade = CascadeType.ALL)
    private List<Goal> goal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "GROUP_ID")
    private Groups group;


}
