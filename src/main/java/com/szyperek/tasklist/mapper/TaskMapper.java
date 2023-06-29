package com.szyperek.tasklist.mapper;

import com.szyperek.tasklist.dto.response.TaskResponse;
import com.szyperek.tasklist.entity.TaskEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Mapper(componentModel = "spring")
public abstract class TaskMapper {

    public abstract TaskResponse mapTaskEntityToTaskResponse(TaskEntity taskEntity);

    @AfterMapping
    protected void formatDate(@MappingTarget TaskResponse taskResponse) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dueDate = LocalDateTime.parse(taskResponse.getDueDate());
        taskResponse.setDueDate(dueDate.format(formatter));

        LocalDateTime creationDate = LocalDateTime.parse(taskResponse.getCreationDate());
        taskResponse.setCreationDate(creationDate.format(formatter));
    }
}
