package com.project.doWith.repository;

import com.project.doWith.domain.DatePeriod;
import com.project.doWith.domain.Groups;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface GroupRepository extends JpaRepository<Groups, Long> {
    Optional<Groups> findByGroupUuid(String groupUuid);
    //파라미터 받아 탐색
    @Query("SELECT g FROM Groups g JOIN g.datePeriods dp WHERE dp = :datePeriod")
    Optional<Groups> findByDatePeriodsContaining(@Param("datePeriod") DatePeriod datePeriod);
}