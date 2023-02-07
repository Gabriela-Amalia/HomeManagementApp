package com.example.homemanagement.mapper;

import com.example.homemanagement.dto.task.CreateTaskDto;
import com.example.homemanagement.dto.task.UpdateTaskDto;
import com.example.homemanagement.model.factory.Task;
import com.example.homemanagement.model.factory.TaskFactory;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    public Task createTaskDtoToTask(CreateTaskDto createTaskDto) {
        return TaskFactory.createTask(
                createTaskDto.getPriority(),
                createTaskDto.getTitle(),
                createTaskDto.getDescription(),
                createTaskDto.getHousehold()
        );
    }

    public Task updateTaskDtoToTask(UpdateTaskDto updateTaskDto) {
        return TaskFactory.createTask(
                updateTaskDto.getPriority(),
                updateTaskDto.getId(),
                updateTaskDto.getTitle(),
                updateTaskDto.getDescription(),
                updateTaskDto.isDone(),
                updateTaskDto.getMember(),
                updateTaskDto.getHousehold()
        );
    }
}
