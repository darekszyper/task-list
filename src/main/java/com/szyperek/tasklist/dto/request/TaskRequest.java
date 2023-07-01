package com.szyperek.tasklist.dto.request;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
public class TaskRequest {

    @NotBlank(message = "Title can't be empty.")
    @Size(max = 100, message = "Title can't exceed 100 characters.")
    private String title;
    @Size(max = 1000, message = "Description can't exceed 1000 characters.")
    private String description;
    @Future(message = "Date must be in the future")
    @NotNull(message = "Due date can't be empty.")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dueDate;
}
