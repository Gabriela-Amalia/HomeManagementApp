package com.example.homemanagement.exception.task;

public class CanNotAssignTaskException extends RuntimeException {

    public CanNotAssignTaskException() { super("Can not assign task to member. Tasks limit per member reached!");
    }
}