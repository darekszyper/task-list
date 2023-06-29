package com.szyperek.tasklist.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class TaskResponse {

    private Long id;
    private String title;
    private String description;
    private String creationDate;
    private String dueDate;
    private boolean isFinished;
}
