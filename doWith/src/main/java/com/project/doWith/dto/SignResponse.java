package com.project.doWith.dto;

import com.project.doWith.domain.Member;
import com.project.doWith.security.login.Authority;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SignResponse {

    private Long id;

    private String account;

    private String name;

    private String email;

    private String profile;

    private List<Authority> roles=new ArrayList<>();

    private String token;


    public SignResponse(Member member){
        this.id=member.getMember_id();
        this.account=member.getAccount();
        this.name=member.getName();
        this.email=member.getEmail();
        this.profile= member.getProfile();
        this.roles=member.getRoles();
    }
}