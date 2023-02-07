package com.example.homemanagement.model.factory;

import com.example.homemanagement.model.Household;
import com.example.homemanagement.model.Member;

public class TaskFactory {
    public static Task createTask(Priority priority, String title, String description, Household household) {
        switch (priority) {
            case HIGH:
                return new HighPriorityTask(title, description, household);
            case MEDIUM:
                return new MediumPriorityTask(title, description, household);
            case LOW:
                return new LowPriorityTask(title, description, household);
            default:
                throw new IllegalArgumentException("Invalid task priority");
        }
    }

    public static Task createTask(Priority priority, long id, String title, String description, boolean isDone, Member member, Household household) {
        switch (priority) {
            case HIGH:
                return new HighPriorityTask(id, title, description, isDone, member, household);
            case MEDIUM:
                return new MediumPriorityTask(id, title, description, isDone, member, household);
            case LOW:
                return new LowPriorityTask(id, title, description, isDone, member, household);
            default:
                throw new IllegalArgumentException("Invalid task priority");
        }
    }
}
