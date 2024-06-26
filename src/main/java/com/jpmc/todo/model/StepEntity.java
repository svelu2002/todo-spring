package com.jpmc.todo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "steps")
public class StepEntity implements BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false)
    private int ordinalNumber;

    @Column(nullable = false, unique = true)
    private String instruction;

    @ManyToOne
    private TaskEntity task;
}
