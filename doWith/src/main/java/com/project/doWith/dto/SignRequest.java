package com.project.doWith.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
public class SignRequest {

    private Long id;

    private String account;

    private String password;

    private String name;

    private String email;

    private String profile;

}