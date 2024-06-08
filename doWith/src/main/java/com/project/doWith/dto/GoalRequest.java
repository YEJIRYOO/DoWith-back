package com.project.doWith.dto;

import com.project.doWith.domain.DatePeriod;
import lombok.Data;

@Data
public class GoalRequest {

    private Integer range;
    //공통->0, 개인->1
    private String confirm_name;
    private String confirm_img;
    private String confirm_review;
    private Boolean complete;
}
