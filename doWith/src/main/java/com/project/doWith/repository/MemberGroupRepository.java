package com.project.doWith.repository;

import com.project.doWith.domain.Member_Group;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface MemberGroupRepository extends JpaRepository<Member_Group,Long> {
}
