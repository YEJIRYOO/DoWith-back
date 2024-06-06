package com.project.doWith.exception;

public class DuplicatedMemberNameException extends RuntimeException {
    public DuplicatedMemberNameException() {
        super("이미 존재하는 account입니다.");
    }
}