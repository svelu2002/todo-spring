package com.jpmc.todo.exception;

import javafx.concurrent.Task;

public class TaskNotFoundException extends Exception {
    public TaskNotFoundException(String message) {
        super(message);
    }
}
