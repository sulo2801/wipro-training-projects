package com.example.bean;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Task {

    @Id
    private Integer id;      

    private String title;

    private String description;

    private String assignedTo;

    private String priority;   

    private String status;     

    private LocalDate dueDate; 
}
