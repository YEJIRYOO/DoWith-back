package com.project.doWith.dto;

import lombok.Data;

import java.time.LocalDate;

@Data
public class DatePeriodCreateRequest {

    private LocalDate start_date;
    private LocalDate end_date;

}
