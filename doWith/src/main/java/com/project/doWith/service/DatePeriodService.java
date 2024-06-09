package com.project.doWith.service;

import com.project.doWith.domain.DatePeriod;
import com.project.doWith.domain.Goal;
import com.project.doWith.domain.Groups;
import com.project.doWith.dto.DateInfoResponse;
import com.project.doWith.dto.DatePeriodCreateRequest;
import com.project.doWith.repository.DatePeriodRepository;
import com.project.doWith.repository.GoalRepository;
import com.project.doWith.repository.GroupRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class DatePeriodService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private DatePeriodRepository datePeriodRepository;

    @Autowired
    private GoalRepository goalRepository;
    public Long createDatePeriod(Long group_id, DatePeriodCreateRequest datePeriodCreateRequest){

        Groups group = groupRepository.findById(group_id)
                .orElseThrow(() -> new IllegalArgumentException("Group with id " + group_id + " not found."));

        // 그룹 정보를 확인하는 로그 추가
        System.out.println("Creating DatePeriod for group: " + group.getGroup_id());

        DatePeriod createdDatePeriod=DatePeriod.builder()
                .start_date(datePeriodCreateRequest.getStart_date())
                .end_date(datePeriodCreateRequest.getEnd_date())
                .achieve_rate(0)
                .group(group)
                .build();
        DatePeriod savedDatePeriod=datePeriodRepository.save(createdDatePeriod);

        return savedDatePeriod.getDate_id();
        //생성된 date_id return
    }

    public DateInfoResponse getDatePeriodInfo(Long date_id) {
        DateInfoResponse dateInfoResponse = new DateInfoResponse();
        Optional<DatePeriod> datePeriodOpt = datePeriodRepository.findById(date_id);

        // DatePeriod가 존재하는지 확인
        DatePeriod datePeriod = datePeriodOpt.orElseThrow(() ->
                new IllegalArgumentException("DatePeriod not found for id: " + date_id));
        dateInfoResponse.setStart_date(datePeriod.getStart_date());
        dateInfoResponse.setEnd_date(datePeriod.getEnd_date());
        dateInfoResponse.setAchieve_rate(datePeriod.getAchieve_rate());

        // 목표 가져오기
        List<Goal> optionGoals = goalRepository.findByDatePeriod(datePeriod);
        dateInfoResponse.setGoal(optionGoals);

        // 그룹 가져오기
        Optional<Groups> groupsOpt = groupRepository.findByDatePeriodsContaining(datePeriod);
        Groups groups = groupsOpt.orElseThrow(() ->
                new IllegalArgumentException("Groups not found for date period id: " + date_id));

        dateInfoResponse.setGroup_id(groups.getGroup_id());

        return dateInfoResponse;
    }
}
