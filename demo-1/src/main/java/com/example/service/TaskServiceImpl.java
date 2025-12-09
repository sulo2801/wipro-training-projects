package com.example.service;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bean.Task;
import com.example.repo.TaskRepository;

@Service
public class TaskServiceImpl implements TaskService {

    @Autowired
    private TaskRepository repo;

    @Override
    public Task addTask(Task task) {
        return repo.save(task);
    }

    @Override
    public Task updateTask(Task task) {
        return repo.save(task);
    }

    @Override
    public void deleteTask(Integer id) {
        repo.deleteById(id);
    }

    @Override
    public List<Task> getAllTasks() {
        return repo.findAll();
    }

    @Override
    public List<Task> getTasksByStatus(String status) {
        return repo.findByStatusIgnoreCase(status);
    }

    @Override
    public List<Task> getTasksByPriority(String priority) {
        return repo.findByPriorityIgnoreCase(priority);
    }

    @Override
    public List<Task> getOverdueTasks() {
        LocalDate today = LocalDate.now();
        return repo.findByDueDateBeforeAndStatusNot(today, "completed");
    }
}
