package com.example.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.bean.Task;
import com.example.service.TaskService;

@RestController
@RequestMapping("/task")
public class TaskController {

    @Autowired
    private TaskService service;

    private static final Set<String> VALID_PRIORITIES = 
        Set.of("low", "medium", "high");

    private static final Set<String> VALID_STATUSES =
        Set.of("pending", "in-progress", "completed");

    private boolean invalid(Task task) {
        return !VALID_PRIORITIES.contains(task.getPriority().toLowerCase()) ||
               !VALID_STATUSES.contains(task.getStatus().toLowerCase()) ||
               task.getDueDate() == null;
    }

    // POST /task
    @PostMapping
    public ResponseEntity<?> addTask(@RequestBody Task task) {

        if (invalid(task)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body("Invalid priority, status or date");
        }

        boolean exists = service.getAllTasks().stream()
                .anyMatch(t -> t.getId().equals(task.getId()));

        if (exists) {
            return ResponseEntity.status(HttpStatus.CONFLICT)
                                 .body("Task with same id exists");
        }

        return new ResponseEntity<>(service.addTask(task), HttpStatus.CREATED);
    }

    // PUT /task
    @PutMapping
    public ResponseEntity<?> updateTask(@RequestBody Task task) {

        if (invalid(task)) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body("Invalid priority, status or date");
        }

        boolean exists = service.getAllTasks().stream()
                .anyMatch(t -> t.getId().equals(task.getId()));

        if (!exists) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Task not found");
        }

        return ResponseEntity.ok(service.updateTask(task));
    }

    // DELETE /task/{id}
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Integer id) {

        boolean exists = service.getAllTasks().stream()
                .anyMatch(t -> t.getId().equals(id));

        if (!exists) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .body("Task not found");
        }

        service.deleteTask(id);
        return ResponseEntity.ok("Task deleted");
    }

    // GET /task
    @GetMapping
    public ResponseEntity<List<Task>> getAllTasks() {
        return ResponseEntity.ok(service.getAllTasks());
    }

    // GET /task/search?status=...
    @GetMapping("/search")
    public ResponseEntity<?> getByStatus(@RequestParam(required = false) String status) {

        if (status == null || status.isBlank()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                                 .body("Status is required");
        }

        return ResponseEntity.ok(service.getTasksByStatus(status));
    }

    // GET /task/priority/high
    @GetMapping("/priority/{priority}")
    public ResponseEntity<List<Task>> getByPriority(@PathVariable String priority) {
        return ResponseEntity.ok(service.getTasksByPriority(priority));
    }

    // GET /task/overdue
    @GetMapping("/overdue")
    public ResponseEntity<List<Task>> getOverdue() {
        return ResponseEntity.ok(service.getOverdueTasks());
    }
}
