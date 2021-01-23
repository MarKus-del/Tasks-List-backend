package com.markusdel.todolist.repository;

import com.markusdel.todolist.model.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface TasksRepository extends JpaRepository<Tasks, Long> {

    List<Tasks> findAllByUserId(Long userId);
}
