package com.szyperek.tasklist.repository;

import com.szyperek.tasklist.entity.TaskEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

    List<TaskEntity> findAllByOrderByDueDateAsc();
}
