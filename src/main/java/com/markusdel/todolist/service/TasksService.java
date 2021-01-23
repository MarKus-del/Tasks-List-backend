package com.markusdel.todolist.service;

import com.markusdel.todolist.model.Tasks;
import com.markusdel.todolist.repository.TasksRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
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
