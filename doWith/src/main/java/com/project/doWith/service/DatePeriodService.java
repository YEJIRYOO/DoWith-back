package com.project.doWith.service;

import com.project.doWith.domain.DatePeriod;
import com.project.doWith.dto.DatePeriodCreateRequest;
import com.project.doWith.repository.DatePeriodRepository;
import com.project.doWith.repository.GroupRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class DatePeriodService {

    @Autowired
    private GroupRepository groupRepository;

    @Autowired
    private DatePeriodRepository datePeriodRepository;

    public Long createDatePeriod(Long group_id, DatePeriodCreateRequest datePeriodCreateRequest){
        DatePeriod createdDatePeriod=DatePeriod.builder()
                .start_date(datePeriodCreateRequest.getStart_date())
                .end_date(datePeriodCreateRequest.getEnd_date())
                .achieve_rate(0)
                .build();
        DatePeriod savedDatePeriod=datePeriodRepository.save(createdDatePeriod);

        return savedDatePeriod.getDate_id();
        //생성된 date_id return
    }

}
