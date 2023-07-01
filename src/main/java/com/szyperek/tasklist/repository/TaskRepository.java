package com.szyperek.tasklist.repository;

import com.szyperek.tasklist.entity.TaskEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TaskRepository extends JpaRepository<TaskEntity, Long> {

    List<TaskEntity> findAllByOrderByDueDateAsc();

    @Modifying
    @Transactional
    @Query(value = "UPDATE tasks SET is_finished = NOT is_finished WHERE id = :id", nativeQuery = true)
    void changeIsFinished(Long id);
}
