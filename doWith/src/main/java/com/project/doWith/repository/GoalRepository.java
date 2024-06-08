package com.project.doWith.repository;

import com.project.doWith.domain.DatePeriod;
import com.project.doWith.domain.Goal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoalRepository extends JpaRepository<Goal,Long> {
}
