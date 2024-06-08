package com.project.doWith.controller;


import com.project.doWith.dto.DatePeriodCreateRequest;
import com.project.doWith.service.DatePeriodService;
import com.project.doWith.service.GoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/date")
public class DateGoalController {

    @Autowired
    private DatePeriodService datePeriodService;

    @Autowired
    private GoalService goalService;

    @PostMapping("/post/{u_g_id}")
    public ResponseEntity<Long> createDate(
            @PathVariable("u_g_id") Long u_g_id, @RequestBody DatePeriodCreateRequest datePeriodCreateRequest){

        Long created_date_id= datePeriodService.createDatePeriod(u_g_id,datePeriodCreateRequest);
        return ResponseEntity.ok(created_date_id);
    }

    @PostMapping("/date/goal/post/{date_id}")
    public ResponseEntity<Long> createGoal(
            @PathVariable("date_id")Long date_id, @RequestBody Map<String, Object> body) {

        Integer range_val = (Integer) body.get("range_val");
        String confirmName = (String) body.get("confirm_name");

        Long savedGoalId = goalService.createGoal(date_id, range_val, confirmName);

        return ResponseEntity.ok(savedGoalId);
    }
}
