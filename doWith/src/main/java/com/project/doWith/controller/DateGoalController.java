package com.project.doWith.controller;


import com.project.doWith.dto.DatePeriodCreateRequest;
import com.project.doWith.dto.GroupCreateRequest;
import com.project.doWith.service.DateGoalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/date")
public class DateGoalController {

    @Autowired
    private DateGoalService dateGoalService;

    @PostMapping("/post/{u_g_id}")
    public ResponseEntity<Long> createDate(
            @PathVariable("u_g_id") Long u_g_id, @RequestBody DatePeriodCreateRequest datePeriodCreateRequest){

        Long created_date_id= dateGoalService.createDatePeriod(u_g_id,datePeriodCreateRequest);
        return ResponseEntity.ok(created_date_id);
    }
}