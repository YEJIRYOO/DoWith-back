package com.project.doWith.repository;

import com.project.doWith.domain.DatePeriod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DatePeriodRepository extends JpaRepository<DatePeriod,Long> {
}
