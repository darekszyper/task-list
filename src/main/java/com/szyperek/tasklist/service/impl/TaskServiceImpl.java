package com.szyperek.tasklist.service.impl;

import com.szyperek.tasklist.dto.response.TaskResponse;
import com.szyperek.tasklist.entity.TaskEntity;
import com.szyperek.tasklist.mapper.TaskMapper;
import com.szyperek.tasklist.repository.TaskRepository;
import com.szyperek.tasklist.service.TaskService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final TaskMapper taskMapper;

    @Override
    public List<TaskResponse> getAllTasksOrderedByDueDateAsc() {
        return taskRepository.findAllByOrderByDueDateAsc().stream()
                .map(taskMapper::mapTaskEntityToTaskResponse)
                .collect(Collectors.toList());
    }

    @Override
    public void deleteTaskById(Long id) {
        if (taskRepository.existsById(id)) {
            taskRepository.deleteById(id);
        }
    }

    @Override
    public void changeIsFinished(Long id) {
        Optional<TaskEntity> optionalTask = taskRepository.findById(id);
        if (optionalTask.isPresent()) {
            TaskEntity task = optionalTask.get();
            task.setFinished(!task.isFinished());
            taskRepository.save(task);
        } else {
            throw new EntityNotFoundException("Task with id: " + id + " not found");
        }
    }
}