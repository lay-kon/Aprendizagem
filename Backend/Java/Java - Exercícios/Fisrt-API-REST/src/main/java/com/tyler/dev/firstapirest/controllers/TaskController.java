package com.tyler.dev.firstapirest.controllers;

import com.tyler.dev.firstapirest.models.Task;
import com.tyler.dev.firstapirest.services.TaskService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/task")
@Validated
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/{id}")
    public ResponseEntity<Task> findTaskById(@PathVariable Long id){
        //return ResponseEntity.ok().body(taskService.findTaskById(id));
        Task task = taskService.findTaskById(id);

        return ResponseEntity.ok().body(task);
        //return ResponseEntity.ok(task);
    }

    @PostMapping
    @Validated(Task.CreateTask.class)
    public ResponseEntity<Void> createTask(@Valid @RequestBody Task task){

        taskService.createTask(task);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequestUri().path("/{id}").buildAndExpand(task.getId()).toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    @Validated(Task.UpdateTask.class)
    public ResponseEntity<Void> updateTask(@Valid @RequestBody Task task, @PathVariable Long id){
        task.setId(id);
        taskService.updateTask(task);

        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id){
        taskService.deleteTask(id);

        return ResponseEntity.noContent().build();
    }

}