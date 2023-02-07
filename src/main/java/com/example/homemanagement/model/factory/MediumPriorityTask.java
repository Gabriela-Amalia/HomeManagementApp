package com.example.homemanagement.model.factory;

import com.example.homemanagement.model.Household;
import com.example.homemanagement.model.Member;

import java.util.List;

public class MediumPriorityTask extends Task {

    static {
        maximumNumberOfTasks = 4;
    }

    public MediumPriorityTask(String title, String description, Household household) {
        super(title, description, household);
    }

    public MediumPriorityTask(long id, String title, String description, boolean isDone, Member member, Household household) {
        super(id, title, description, isDone, member, household);
    }

    @Override
    public String getEmailMessage() {
        return "You have been assigned to a medium priority task!";
    }

    @Override
    public boolean canAssign(List<Task> tasks) {
        int mediumPriorityCount = 0;

        for (Task task : tasks) {
            if (task instanceof MediumPriorityTask) {
                mediumPriorityCount++;
            }
        }

        return mediumPriorityCount < maximumNumberOfTasks;
    }
}
