package com.project.doWith.repository;

import com.project.doWith.domain.DatePeriod;
import com.project.doWith.domain.Goal;
import com.project.doWith.domain.Groups;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface GoalRepository extends JpaRepository<Goal,Long> {
    List<Goal> findByDatePeriod(DatePeriod datePeriod);
}
