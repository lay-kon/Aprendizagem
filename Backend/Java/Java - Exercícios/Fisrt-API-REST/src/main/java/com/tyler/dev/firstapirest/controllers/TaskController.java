package com.tyler.dev.firstapirest.controllers;

import com.tyler.dev.firstapirest.models.Task;
import com.tyler.dev.firstapirest.services.TaskService;
import com.tyler.dev.firstapirest.services.UserService;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/task")
@Validated
public class TaskController {

    @Autowired
    private TaskService taskService;

    @Autowired
    private UserService userService;

    @GetMapping("/{id}")
    public ResponseEntity<Task> findTaskById(@PathVariable Long id){
        //return ResponseEntity.ok().body(taskService.findTaskById(id));
        Task task = taskService.findTaskById(id);

        return ResponseEntity.ok().body(task);
        //return ResponseEntity.ok(task);
    }

    @GetMapping("user/{id}")
    public ResponseEntity<List<Task>> findAllTaskById(@PathVariable Long id){
        List<Task> tasks = taskService.findAllTaskById(id);

        return ResponseEntity.ok().body(tasks);
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