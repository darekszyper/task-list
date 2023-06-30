package com.szyperek.tasklist.service;

import com.szyperek.tasklist.dto.request.TaskRequest;
import com.szyperek.tasklist.dto.response.TaskResponse;

import java.util.List;

public interface TaskService {

    List<TaskResponse> getAllTasksOrderedByDueDateAsc();

    void deleteTaskById(Long id);

    void changeIsFinished(Long id);

    void addTask(TaskRequest taskRequest);
}
