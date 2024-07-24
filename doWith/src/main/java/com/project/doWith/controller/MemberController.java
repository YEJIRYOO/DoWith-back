package com.project.doWith.controller;

import com.project.doWith.domain.Member;
import com.project.doWith.dto.ShareInfoResponse;
import com.project.doWith.dto.ShareRequest;
import com.project.doWith.dto.SignResponse;
import com.project.doWith.service.MemberGroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberGroupService memberService;

    @PutMapping("/member/put/{member_id}")
    public ResponseEntity<Optional<Member>> putMemberInfo(
            @PathVariable("member_id")Long member_id, @RequestBody SignResponse updateMemberRequest) {
        Optional<Member> memberOptional=memberService.putMemberInfo(member_id,updateMemberRequest);
        return ResponseEntity.ok(memberOptional);
    }


}
