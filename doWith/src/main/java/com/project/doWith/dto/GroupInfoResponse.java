package com.project.doWith.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class GroupInfoResponse {

    private Long group_id;
    private String group_intro;
    private String group_info;
    private String group_uuid;

}
