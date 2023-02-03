package com.example.homemanagement.dto.task;

import com.example.homemanagement.model.Household;
import com.example.homemanagement.model.Member;

public class UpdateTaskDto extends CreateTaskDto{

    private long id;

    private boolean isDone;

    private Member member;

    public UpdateTaskDto() {
    }

    public UpdateTaskDto(String title, String description, long id, boolean isDone, Member member, Household household) {
        super(title, description, household);
        this.id = id;
        this.member = member;
        this.isDone = isDone;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }
}
