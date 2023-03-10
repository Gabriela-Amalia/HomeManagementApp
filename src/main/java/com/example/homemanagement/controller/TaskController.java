package com.example.homemanagement.controller;

import com.example.homemanagement.dto.task.CreateTaskDto;
import com.example.homemanagement.dto.task.UpdateTaskDto;
import com.example.homemanagement.mapper.TaskMapper;
import com.example.homemanagement.model.factory.Task;
import com.example.homemanagement.observerPattern.EmailObserver;
import com.example.homemanagement.observerPattern.EmailSender;
import com.example.homemanagement.service.TaskService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;

    private final TaskMapper taskMapper;

    private EmailSender emailSender = new EmailSender();
    private EmailObserver emailObserver = new EmailObserver();

    public TaskController(TaskService taskService, TaskMapper taskMapper) {
        this.taskService = taskService;
        this.taskMapper = taskMapper;

        emailSender.attach(emailObserver);
    }

    @PostMapping
    public ResponseEntity<Task> create(
            @RequestBody
            @Valid
            CreateTaskDto createTaskDto
    ) {
        Task task = taskMapper.createTaskDtoToTask(createTaskDto);
        Task createdTask = taskService.create(task);
        return ResponseEntity.created(URI.create("/tasks/" + createdTask.getId()))
                .body(createdTask);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Task> update(
            @PathVariable
            long id,
            @RequestBody
            @Valid
            UpdateTaskDto updateTaskDto) {

        if(id != updateTaskDto.getId()) {
            throw new RuntimeException("IDs must match");
        }

        Task task = taskMapper.updateTaskDtoToTask(updateTaskDto);
        Task updatedTask = taskService.update(task);
        return ResponseEntity.ok()
                .body(updatedTask);
    }

    @PutMapping()
    public ResponseEntity<Task> updateTaskMember(
            @RequestParam(required = true)
            Long taskId,
            @RequestParam(required = true)
            Long memberId) {
        Task task = taskService.assign(taskId, memberId);

        emailObserver.setRecipientEmail(task.getMember().getEmail());
        emailObserver.setMessage(task.getEmailMessage());
        emailSender.notifyObservers();

        return ResponseEntity.ok()
                .body(task);
    }

    @PutMapping("finishTask/{id}")
    public ResponseEntity<Task> finishTask(
            @PathVariable
            long id) {
        Task task = taskService.finishTask(id);
        return ResponseEntity.ok()
                .body(task);
    }

    @GetMapping
    public List<Task> get(
            @RequestParam(required = false)
            Long householdId,
            @RequestParam(required = false)
            Long memberId) {

        return taskService.get(householdId, memberId);
    }
}
