package com.szyperek.tasklist.mapper;

import com.szyperek.tasklist.dto.request.TaskRequest;
import com.szyperek.tasklist.entity.TaskEntity;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import java.time.LocalDateTime;

@Mapper(componentModel = "spring")
public abstract class TaskRequestMapper {

    public abstract TaskEntity mapTaskRequestToTaskEntity(TaskRequest taskRequest);

    @AfterMapping
    protected void setCurrentTimeStamp(@MappingTarget TaskEntity taskEntity) {
        taskEntity.setCreationDate(LocalDateTime.now());
    }
}
