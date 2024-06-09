package com.project.doWith.service;

import com.project.doWith.domain.Goal;
import com.project.doWith.dto.GoalInfoResponse;
import com.project.doWith.dto.GoalRequest;
import com.project.doWith.repository.GoalRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class GoalService {

    @Autowired
    private GoalRepository goalRepository;

    public Long createGoal(Long date_id, Integer range, String confirm_name){
        Goal createGoal= Goal.builder()
                .complete(false)
                .confirm_img(null)
                .confirm_review(null)
                .range_val(range)
                .confirm_name(confirm_name)
                .build();
        Goal savedGoal=goalRepository.save(createGoal);

        return savedGoal.getGoal_id();
    }
    public GoalInfoResponse getGoalInfo(Long goal_id){
        GoalInfoResponse goalInfoResponse=new GoalInfoResponse();

        Optional<Goal> optionalGoal=goalRepository.findById(goal_id);
        goalInfoResponse.setComplete(optionalGoal.get().getComplete());
        goalInfoResponse.setRange_val(optionalGoal.get().getRange_val());
        goalInfoResponse.setConfirm_name(optionalGoal.get().getConfirm_name());
        goalInfoResponse.setConfirm_img(optionalGoal.get().getConfirm_img());
        goalInfoResponse.setConfirm_review(optionalGoal.get().getConfirm_review());

        goalInfoResponse.setGoal_id(goal_id);

        return goalInfoResponse;
    }
}
