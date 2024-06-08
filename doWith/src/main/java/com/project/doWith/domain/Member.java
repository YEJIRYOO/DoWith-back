package com.project.doWith.domain;

import com.project.doWith.security.login.Authority;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "MEMBER")
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_ID")
    private Long member_id;

    @Column(name = "ACCOUNT",nullable = false,unique = true)
    private String account;

    @Column(name = "EMAIL",nullable = false,unique = true)
    private String email;

    @Column(name = "NAME",nullable = false)
    private String name;

    @Column(name = "PASSWORD",nullable = false)
    private String password;

    @Column(name = "PROFILE",nullable = true)
    private String profile;

    //hashSet로 관리_고아 객체 delete
    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Member_Group> userGroups = new HashSet<>();

    //fetch_속성 데이터 어떻게 로드 할 지_즉시 로드
    //Lazy: 실제로 데이터에 접근할 때까지 로드하지 않음
    @OneToMany(mappedBy = "member",fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @Builder.Default//빌더 패턴 생성
    private List<Authority> roles=new ArrayList<>();

    public void setRoles(List<Authority> role){
        this.roles=role;
        role.forEach(o -> o.setMember(this));
        //Authority 엔터티 순회하며 setMember 메서드 호출
    }
}
