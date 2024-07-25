package com.project.doWith.service;

import com.project.doWith.domain.Groups;
import com.project.doWith.domain.Member;
import com.project.doWith.dto.GroupInfoResponse;
import com.project.doWith.dto.SignResponse;
import com.project.doWith.exception.DataNotFoundException;
import com.project.doWith.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
@RequiredArgsConstructor
public class MemberGroupService {
    MemberRepository memberRepository;
    public Optional<Member> putMemberInfo(Long member_id,SignResponse updateMemberRequest){
        SignResponse signResponse=new SignResponse();
        Optional<Member> optionalMember=memberRepository.findById(member_id);
        if(optionalMember.isPresent()){
            Member member=optionalMember.get();
            member.setEmail(updateMemberRequest.getEmail());
            member.setProfile(updateMemberRequest.getProfile());
            memberRepository.save(member);

            return optionalMember;
            //member는 Membertype이므로 타입 충돌로 반환 불가
        }
        else throw new DataNotFoundException("Member does not exist");
    }
}
