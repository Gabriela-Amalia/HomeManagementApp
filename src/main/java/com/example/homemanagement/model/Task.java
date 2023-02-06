package com.example.homemanagement.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tasks")
public class Task {

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

    @Enumerated(EnumType.STRING)
    private Priority priority;

    public Task() {
    }

    public Task(String title, String description, Household  household, Priority priority) {
        this.title = title;
        this.description = description;
        this.isDone = false;
        this.household = household;
        this.priority = priority;
    }

    public Task(long id, String title, String description, boolean isDone, Member member, Household household, Priority priority) {
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

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }
}