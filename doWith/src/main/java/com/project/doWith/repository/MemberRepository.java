package com.project.doWith.repository;

import com.project.doWith.domain.Member;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


/*
사용자 조회 repo
account를 이용하여 조회 _email 로그인 시 findByEmail
 */
@Transactional
public interface MemberRepository extends JpaRepository<Member,Long>{
    Optional<Member> findByAccount(String account);
}