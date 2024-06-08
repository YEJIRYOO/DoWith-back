package com.project.doWith.controller;

import com.project.doWith.domain.Member;
import com.project.doWith.dto.GroupCreateRequest;
import com.project.doWith.dto.GroupInfoResponse;
import com.project.doWith.dto.ShareInfoResponse;
import com.project.doWith.dto.ShareRequest;
import com.project.doWith.service.GroupService;
import com.project.doWith.service.ShareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/group")
public class GroupController {

    @Autowired
    private GroupService groupService;

    @Autowired
    private ShareService shareService;

    @PostMapping("/post/{member_id}")
    public ResponseEntity<Long> createGroup(
            @PathVariable("member_id") Long member_id, @RequestBody GroupCreateRequest groupCreateRequest){

        Long created_group_id= groupService.createGroup(member_id,groupCreateRequest);
        return ResponseEntity.ok(created_group_id);
    }

    @GetMapping("/get/{group_id}")
    public ResponseEntity<GroupInfoResponse> getGroupInfo(@PathVariable("group_id") Long group_id) {
        GroupInfoResponse groupInfoResponse = groupService.getGroupInfo(group_id);
        return ResponseEntity.ok(groupInfoResponse);
    }


    @PostMapping("/share/enter/{member_id}")
    public ResponseEntity<ShareInfoResponse> shareInfo(
            @PathVariable("member_id") Long member_id, @RequestBody Map<String, String> requestBody) {
        String groupUuid = requestBody.get("groupUuid");
        ShareInfoResponse shareInfoResponse = shareService.joinGroupByUUID(member_id, groupUuid);
        return ResponseEntity.ok(shareInfoResponse);
    }
}
