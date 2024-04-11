package com.jpmc.todo.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;

@Entity
public class StepEntity implements BaseEntity {
    private int serialNumber;
    private String instruction;

    @ManyToOne
    private TaskEntity task;
}
