package com.markusdel.todolist.service;

import com.markusdel.todolist.model.Tasks;
import com.markusdel.todolist.repository.TasksRepository;

import java.util.List;
import java.util.Optional;

public class TasksService {

    private TasksRepository tasksRepository;

    public TasksService(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    public List<Tasks> getAllTasks(Long idUser){
        List<Tasks> allByUserId = tasksRepository.findAllByUserId(idUser);
        return allByUserId;
    }

    public Tasks getTasksById(Long id) {
        Optional<Tasks> byId = tasksRepository.findById(id);
        return byId.get();
    }

    public Tasks createTasks(Tasks tasks) {
        return tasksRepository.save(tasks);
    }
}
