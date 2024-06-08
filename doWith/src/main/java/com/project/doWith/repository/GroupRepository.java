package com.project.doWith.repository;

import com.project.doWith.domain.Groups;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GroupRepository extends JpaRepository<Groups, Long> {
    Optional<Groups> findByGroupUuid(String groupUuid);
}