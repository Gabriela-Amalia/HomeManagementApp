package com.example.homemanagement.mapper;

import com.example.homemanagement.dto.task.CreateTaskDto;
import com.example.homemanagement.dto.task.UpdateTaskDto;
import com.example.homemanagement.model.Task;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public Task createTaskDtoToTask(CreateTaskDto createTaskDto) {
        return new Task(
                createTaskDto.getTitle(),
                createTaskDto.getDescription()
        );
    }

    public Task updateTaskDtoToTask(UpdateTaskDto updateTaskDto) {
        return new Task(updateTaskDto.getId(),
                updateTaskDto.getTitle(),
                updateTaskDto.getDescription(),
                updateTaskDto.getMember(),
                updateTaskDto.isDone());
    }
}
