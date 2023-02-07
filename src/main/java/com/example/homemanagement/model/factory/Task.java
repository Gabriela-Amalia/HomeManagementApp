package com.example.homemanagement.model.factory;

import com.example.homemanagement.model.Household;
import com.example.homemanagement.model.Member;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "tasks")
public abstract class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;

    private String  description;

    private boolean isDone;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "household_id")
    private Household household;

    public static int maximumNumberOfTasks;

    public Task(String title, String description, Household  household) {
        this.title = title;
        this.description = description;
        this.isDone = false;
        this.household = household;
    }

    public Task(long id, String title, String description, boolean isDone, Member member, Household household) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.isDone = isDone;
        this.member = member;
        this.household = household;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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

    public Household getHousehold() {
        return household;
    }

    public void setHousehold(Household household) {
        this.household = household;
    }

    public abstract String getEmailMessage();

    public abstract boolean canAssign(List<Task> tasks);
}