package com.jpmc.todo.repository;

import com.jpmc.todo.model.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<TaskEntity, Integer> {

    @Query("FROM tasks t WHERE t.status = :status")
    List<TaskEntity> getTasksByStatus(@Param("status") String status);
}
