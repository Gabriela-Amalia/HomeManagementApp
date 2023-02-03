package com.example.homemanagement.exception.member;

public class MemberNotFoundException extends RuntimeException {

    public MemberNotFoundException() {
        super("The member doesn't exist.");
    }
}
