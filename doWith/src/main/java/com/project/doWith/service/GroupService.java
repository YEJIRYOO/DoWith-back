package com.project.doWith.service;

import com.project.doWith.domain.Groups;
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
import org.springframework.stereotype.Service;

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
        Groups createdGroups = Groups.builder()
                .group_info(groupCreateRequest.getGroup_info())
                .group_intro(groupCreateRequest.getGroup_intro())
                .groupUuid(UUID.randomUUID().toString())
                .build();
        Groups savedGroups =groupRepository.save(createdGroups);

        Member member=memberRepository.findById(member_id)
                .orElseThrow(()->new IllegalArgumentException("Invalid member ID"));
        //TODO: 예외 일괄 처리

        Member_Group createdMemberGroup=Member_Group.builder()
                .member(member)
                .groups(savedGroups)
                .build();
        Member_Group savedMemGroup=memberGroupRepository.save(createdMemberGroup);

        return savedGroups.getGroup_id();
        //생성된 group_id return
    }

    public GroupInfoResponse getGroupInfo(Long group_id){
        Groups groups =groupRepository.findById(group_id)
                .orElseThrow(()->new IllegalArgumentException("Invalid group ID"));

        GroupInfoResponse groupInfoResponse=new GroupInfoResponse();
        groupInfoResponse.setGroup_id(groups.getGroup_id());
        groupInfoResponse.setGroup_info(groups.getGroup_info());
        groupInfoResponse.setGroup_intro(groups.getGroup_intro());
        groupInfoResponse.setGroup_uuid(groups.getGroupUuid());

        return groupInfoResponse;
    }


}
