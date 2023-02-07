package com.example.homemanagement.service;

import com.example.homemanagement.exception.member.MemberNotFoundException;
import com.example.homemanagement.exception.task.CanNotAssignTaskException;
import com.example.homemanagement.exception.task.TaskNotFoundException;
import com.example.homemanagement.model.Member;
import com.example.homemanagement.model.factory.Task;
import com.example.homemanagement.repository.MemberRepository;
import com.example.homemanagement.repository.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;
    private final MemberRepository memberRepository;

    public TaskService(TaskRepository taskRepository, MemberRepository memberRepository) {
        this.taskRepository = taskRepository;
        this.memberRepository = memberRepository;
    }

    public Task create(Task task) {
        return taskRepository.save(task);
    }

    public Task update(Task task) {
        Optional<Task> existingTask = taskRepository.findById(task.getId());
        if(existingTask.isEmpty()) {
            throw new TaskNotFoundException(task.getId());
        }

        return taskRepository.save(task);
    }

    public Task assign(long taskId, long memberId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(taskId));
        Member member = memberRepository.findById(memberId).orElseThrow(() -> new MemberNotFoundException());

        List<Task> allTasksOfMember = taskRepository.findByMember_IdAndIsDone(memberId, false);
        if (!task.canAssign(allTasksOfMember)) {
            throw new CanNotAssignTaskException();
        }

        task.setMember(member);
        return taskRepository.save(task);
    }

    public Task finishTask(long taskId) {
        Task task = taskRepository.findById(taskId).orElseThrow(() -> new TaskNotFoundException(taskId));
        task.setDone(true);
        return taskRepository.save(task);
    }

    public List<Task> get(Long householdId, Long memberId) {
        List<Task> tasks = new ArrayList<>();

        if(householdId != null) {
            if(memberId != null) {
                tasks = taskRepository.findByHousehold_IdAndMember_Id(householdId, memberId);
            } else {
                tasks = taskRepository.findByHousehold_Id(householdId);
            }
        } else {
            if(memberId != null) {
                tasks = taskRepository.findByMember_Id(memberId);
            } else {
                // find all
                tasks = taskRepository.findAll();
            }
        }
        return tasks;
    }
}
