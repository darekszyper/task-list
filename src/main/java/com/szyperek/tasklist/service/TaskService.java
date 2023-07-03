package com.szyperek.tasklist.service;

import com.szyperek.tasklist.dto.request.TaskRequest;
import com.szyperek.tasklist.dto.response.TaskResponse;

import java.util.List;

public interface TaskService {

    List<TaskResponse> getAllTasksOrderedByDueDateAsc();

    List<TaskResponse> getActiveTasksOrderedByDueDateAsc();

    List<TaskResponse> getFinishedTasksOrderedByDueDateAsc();

    void deleteTaskById(Long id);

    void changeIsFinished(Long id);

    void addTask(TaskRequest taskRequest);

    TaskResponse getTaskById(Long id);

    void editTask(Long id, TaskRequest taskRequest);

}
