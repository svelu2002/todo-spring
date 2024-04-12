package com.jpmc.todo.controller;

import com.jpmc.todo.dto.TaskDTO;
import com.jpmc.todo.exception.TaskAlreadyExistsException;
import com.jpmc.todo.exception.TaskNotFoundException;
import com.jpmc.todo.service.TaskService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/tasks")
@Slf4j
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping
    public ResponseEntity<List<TaskDTO>> getAllTasks() {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.getAllTasks());
    }

    @PostMapping
    public ResponseEntity<TaskDTO> saveTask(@RequestBody @Valid TaskDTO taskDTO) throws TaskAlreadyExistsException {
        log.info("inside TaskController - saveTask");
        return ResponseEntity.status(HttpStatus.CREATED).body(taskService.saveTask(taskDTO));
    }

    @PutMapping(path = "/{id}")
    public ResponseEntity<TaskDTO> updateTask(@PathVariable int id, @RequestBody TaskDTO taskDTO) throws TaskNotFoundException {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(taskService.updateTask(id, taskDTO));
    }

    @DeleteMapping(path = "{id}")
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteTask(@PathVariable int id) throws TaskNotFoundException {
        taskService.deleteTask(id);
    }


    @GetMapping(path = "/{id}")
    public ResponseEntity<TaskDTO> getTaskById(@PathVariable int id) throws TaskNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(taskService.getTaskById(id));
    }



}
