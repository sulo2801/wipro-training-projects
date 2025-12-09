package com.example.service;

import java.util.List;
import com.example.bean.Task;

public interface TaskService {

    Task addTask(Task task);
    Task updateTask(Task task);
    void deleteTask(Integer id);

    List<Task> getAllTasks();
    List<Task> getTasksByStatus(String status);
    List<Task> getTasksByPriority(String priority);
    List<Task> getOverdueTasks();
}
