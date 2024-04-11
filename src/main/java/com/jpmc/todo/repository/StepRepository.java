package com.jpmc.todo.repository;

import com.jpmc.todo.model.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StepRepository extends JpaRepository<TaskEntity, Integer> {
}
