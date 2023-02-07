package com.example.homemanagement.model.factory;

import com.example.homemanagement.model.Household;
import com.example.homemanagement.model.Member;

import java.util.List;

public class LowPriorityTask extends Task {

    static {
        maximumNumberOfTasks = 6;
    }

    public LowPriorityTask(String title, String description, Household household) {
        super(title, description, household);
    }

    public LowPriorityTask(long id, String title, String description, boolean isDone, Member member, Household household) {
        super(id, title, description, isDone, member, household);
    }

    @Override
    public String getEmailMessage() {
        return "You have been assigned to a low priority task!";
    }

    @Override
    public boolean canAssign(List<Task> tasks) {
        int lowPriorityCount = 0;

        for (Task task : tasks) {
            if (task instanceof LowPriorityTask) {
                lowPriorityCount++;
            }
        }

        return lowPriorityCount < maximumNumberOfTasks;
    }

}
