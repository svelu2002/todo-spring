package com.jpmc.todo.repository;

import com.jpmc.todo.model.StepEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StepRepository extends JpaRepository<StepEntity, Integer> {
}
