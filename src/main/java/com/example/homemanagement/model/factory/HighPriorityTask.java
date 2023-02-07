package com.example.homemanagement.model.factory;

import com.example.homemanagement.model.Household;
import com.example.homemanagement.model.Member;

import java.util.List;

public class HighPriorityTask extends Task {

    static {
        maximumNumberOfTasks = 2;
    }

    public HighPriorityTask(String title, String description, Household household) {
        super(title, description, household);
    }

    public HighPriorityTask(long id, String title, String description, boolean isDone, Member member, Household household) {
        super(id, title, description, isDone, member, household);
    }

    @Override
    public String getEmailMessage() {
        return "You have been assigned to a high priority task!";
    }

    @Override
    public boolean canAssign(List<Task> tasks) {
        int highPriorityCount = 0;

        for (Task task : tasks) {
            if (task instanceof HighPriorityTask) {
                highPriorityCount++;
            }
        }

        return highPriorityCount < maximumNumberOfTasks;
    }
}
