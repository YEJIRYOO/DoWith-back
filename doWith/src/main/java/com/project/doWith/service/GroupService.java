package com.project.doWith.service;

import com.project.doWith.domain.Group;
import com.project.doWith.domain.Member;
import com.project.doWith.domain.Member_Group;
import com.project.doWith.dto.GroupCreateRequest;
import com.project.doWith.dto.GroupInfoResponse;
import com.project.doWith.repository.GroupRepository;
import com.project.doWith.repository.MemberGroupRepository;
import com.project.doWith.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class GroupService {
    @Autowired
    private GroupRepository groupRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private MemberGroupRepository memberGroupRepository;
    //정상 생성 시 return group_id
    public Long createGroup(Long member_id,GroupCreateRequest groupCreateRequest){
        Group createdGroup=Group.builder()
                .group_info(groupCreateRequest.getGroup_info())
                .group_intro(groupCreateRequest.getGroup_intro())
                .group_uuid(UUID.randomUUID())
                .build();
        Group savedGroup=groupRepository.save(createdGroup);

        Member member=memberRepository.findById(member_id)
                .orElseThrow(()->new IllegalArgumentException("Invalid member ID"));
        //TODO: 예외 일괄 처리

        Member_Group createdMemberGroup=Member_Group.builder()
                .member(member)
                .group(savedGroup)
                .build();
        Member_Group savedMemGroup=memberGroupRepository.save(createdMemberGroup);

        return savedGroup.getGroup_id();
        //생성된 group_id return
    }

    public GroupInfoResponse getGroupInfo(Long group_id){
        Group group=groupRepository.findById(group_id)
                .orElseThrow(()->new IllegalArgumentException("Invalid group ID"));

        GroupInfoResponse groupInfoResponse=new GroupInfoResponse();
        groupInfoResponse.setGroup_id(group.getGroup_id());
        groupInfoResponse.setGroup_info(group.getGroup_info());
        groupInfoResponse.setGroup_intro(group.getGroup_intro());
        groupInfoResponse.setGroup_uuid(group.getGroup_uuid());

        return groupInfoResponse;
    }


}
