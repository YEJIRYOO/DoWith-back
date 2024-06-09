package com.project.doWith.dto;

import com.project.doWith.domain.DatePeriod;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class GoalInfoResponse {

    private Long goal_id;
//    private DatePeriod datePeriod;
    private Integer range_val;
    private String confirm_name;
    private String confirm_img;
    private String confirm_review;
    private Boolean complete;


}
