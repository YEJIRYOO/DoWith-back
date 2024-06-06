package com.project.doWith.exception;

public class MissingRequestParameterException extends RuntimeException {
    public MissingRequestParameterException() {
        super("정보를 올바르게 작성해주세요.");
    }
}