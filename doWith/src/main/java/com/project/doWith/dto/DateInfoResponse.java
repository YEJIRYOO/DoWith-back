package com.project.doWith.dto;

import com.project.doWith.domain.Goal;
import com.project.doWith.domain.Groups;

import lombok.Data;

import java.time.LocalDate;
import java.util.List;


@Data
public class DateInfoResponse {

    private LocalDate start_date;
    private LocalDate end_date;
    private Integer achieve_rate;
    private List<Goal> goal;
    private Long group_id;

}
