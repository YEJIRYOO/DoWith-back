package com.project.doWith.service;

import com.project.doWith.domain.Groups;
import com.project.doWith.domain.Member;
import com.project.doWith.domain.Member_Group;
import com.project.doWith.dto.ShareInfoResponse;
import com.project.doWith.repository.GroupRepository;
import com.project.doWith.repository.MemberGroupRepository;
import com.project.doWith.repository.MemberRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Transactional
public class ShareService {
    @Autowired
    private final GroupRepository groupRepository;
    @Autowired
    private final MemberRepository memberRepository;
    @Autowired
    private final MemberGroupRepository memberGroupRepository;

    public ShareInfoResponse joinGroupByUUID(Long member_id, String groupUuid) {
        Groups targetGroups = groupRepository.findByGroupUuid(groupUuid)
                .orElseThrow(() -> new IllegalArgumentException("Invalid UUID"));

        Member joinMember = memberRepository.findById(member_id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid member ID"));

        Member_Group addMemberGroup = Member_Group.builder()
                .member(joinMember)
                .groups(targetGroups)
                .build();
        Member_Group savedMemGroup = memberGroupRepository.save(addMemberGroup);

        ShareInfoResponse shareInfoResponse = new ShareInfoResponse();
        shareInfoResponse.setU_g_id(savedMemGroup.getU_g_id());

        return shareInfoResponse;
    }
}