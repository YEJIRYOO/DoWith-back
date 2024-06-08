package com.project.doWith.dto;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class GroupCreateRequest {

    private String group_intro;
    private String group_info;
}
