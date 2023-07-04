package com.szyperek.tasklist.service;

import com.szyperek.tasklist.dto.request.TaskRequest;
import com.szyperek.tasklist.entity.TaskEntity;
import com.szyperek.tasklist.mapper.TaskRequestMapper;
import com.szyperek.tasklist.repository.TaskRepository;
import com.szyperek.tasklist.service.impl.TaskServiceImpl;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class TaskServiceTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private TaskRequestMapper taskRequestMapper;

    @InjectMocks
    private TaskServiceImpl taskService;

    @Test
    void whenAddTask_thenShouldSaveTaskToRepository() {
        // Given
        TaskRequest taskRequest = new TaskRequest();
        TaskEntity taskEntity = new TaskEntity();
        when(taskRequestMapper.mapTaskRequestToTaskEntity(taskRequest)).thenReturn(taskEntity);

        // When
        taskService.addTask(taskRequest);

        // Then
        verify(taskRepository, times(1)).save(taskEntity);
    }

    @Test
    void whenEditTask_WithExistingTaskId_thenShouldUpdateTaskInRepository() {
        // Given
        Long taskId = 1L;
        LocalDateTime dateTimeOfExistingTask = LocalDateTime.of(2025, 1, 1, 10, 0);

        TaskRequest taskRequest = TaskRequest.builder()
                .title("Edited title")
                .dueDate(dateTimeOfExistingTask.plusDays(1).plusHours(1))
                .description("Edited description")
                .build();

        TaskEntity existingTask = TaskEntity.builder()
                .id(taskId)
                .title("Original title")
                .dueDate(dateTimeOfExistingTask)
                .description("Original description")
                .build();

        Optional<TaskEntity> existingTaskOptional = Optional.of(existingTask);
        when(taskRepository.findById(taskId)).thenReturn(existingTaskOptional);

        // When
        taskService.editTask(taskId, taskRequest);

        // Then
        verify(taskRepository, times(1)).save(existingTask);
        assertEquals(taskRequest.getTitle(), existingTask.getTitle());
        assertEquals(taskRequest.getDueDate(), existingTask.getDueDate());
        assertEquals(taskRequest.getDescription(), existingTask.getDescription());
    }

    @Test
    void whenEditTask_withNonExistingTaskId_ShouldThrowEntityNotFoundException() {
        // Given
        Long taskId = 1L;
        TaskRequest taskRequest = new TaskRequest();
        when(taskRepository.findById(taskId)).thenReturn(Optional.empty());

        // Then
        assertThrows(EntityNotFoundException.class, () -> {
            // When
            taskService.editTask(taskId, taskRequest);
        });
    }
}