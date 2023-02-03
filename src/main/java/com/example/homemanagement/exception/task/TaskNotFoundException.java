package com.example.homemanagement.exception.task;

public class TaskNotFoundException extends RuntimeException {

    public TaskNotFoundException(long id) { super("Could not find task with id = " + id);
    }
}
