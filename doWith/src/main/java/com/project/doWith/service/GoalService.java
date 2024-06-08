package com.project.doWith.service;

import com.project.doWith.domain.Goal;
import com.project.doWith.dto.GoalRequest;
import com.project.doWith.repository.GoalRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
