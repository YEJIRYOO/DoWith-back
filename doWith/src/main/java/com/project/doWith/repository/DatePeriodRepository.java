package com.project.doWith.repository;

import com.project.doWith.domain.DatePeriod;
import com.project.doWith.domain.Goal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DatePeriodRepository extends JpaRepository<DatePeriod,Long> {
    Optional<DatePeriod> findByGoal(Goal goal);
}
