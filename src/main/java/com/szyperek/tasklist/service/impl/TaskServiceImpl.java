package com.szyperek.tasklist.service.impl;

import com.szyperek.tasklist.dto.request.TaskRequest;
import com.szyperek.tasklist.dto.response.TaskResponse;
import com.szyperek.tasklist.entity.TaskEntity;
import com.szyperek.tasklist.mapper.TaskRequestMapper;
import com.szyperek.tasklist.mapper.TaskResponseMapper;
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
    private final TaskResponseMapper taskResponseMapper;
    private final TaskRequestMapper taskRequestMapper;

    @Override
    public List<TaskResponse> getAllTasksOrderedByDueDateAsc() {
        return taskRepository.findAllByOrderByDueDateAsc().stream()
                .map(taskResponseMapper::mapTaskEntityToTaskResponse)
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
        if (taskRepository.existsById(id)) {
            taskRepository.changeIsFinished(id);
        } else {
            throw new EntityNotFoundException("Task with id: " + id + " not found");
        }
    }

    @Override
    public void addTask(TaskRequest taskRequest) {
        taskRepository.save(taskRequestMapper.mapTaskRequestToTaskEntity(taskRequest));
    }

    @Override
    public TaskResponse getTaskById(Long id) {
        Optional<TaskEntity> taskEntityOptional = taskRepository.findById(id);
        if (taskEntityOptional.isPresent()) {
            return taskResponseMapper.mapTaskEntityToTaskResponse(taskEntityOptional.get());
        } else {
            throw new EntityNotFoundException("Task with id: " + id + " not found");
        }
    }

    @Override
    public void editTask(Long id, TaskRequest taskRequest) {
        Optional<TaskEntity> taskEntityOptional = taskRepository.findById(id);
        if (taskEntityOptional.isPresent()) {
            TaskEntity existingTask = taskEntityOptional.get();
            existingTask.setTitle(taskRequest.getTitle());
            existingTask.setDueDate(taskRequest.getDueDate());
            existingTask.setDescription(taskRequest.getDescription());
            taskRepository.save(existingTask);
        } else {
            throw new EntityNotFoundException("Task with id: " + id + " not found");
        }
    }
}
