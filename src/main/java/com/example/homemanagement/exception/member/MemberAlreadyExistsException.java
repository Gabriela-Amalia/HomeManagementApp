package com.example.homemanagement.exception.member;

public class MemberAlreadyExistsException extends RuntimeException {

    public MemberAlreadyExistsException() {
        super("A member with the same email already exists.");
    }
}
