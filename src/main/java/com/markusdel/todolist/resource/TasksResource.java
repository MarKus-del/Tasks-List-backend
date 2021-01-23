package com.markusdel.todolist.resource;

import com.markusdel.todolist.dto.UserResponseDTO;
import com.markusdel.todolist.exception.UserNotFoundException;
import com.markusdel.todolist.mapper.UserMapper;
import com.markusdel.todolist.model.Tasks;
import com.markusdel.todolist.model.User;
import com.markusdel.todolist.service.TasksService;
import com.markusdel.todolist.service.UserService;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/{id}/tasks")
public class TasksResource {

    private TasksService tasksService;
    private UserService userService;
    private UserMapper userMapper;

    public TasksResource(TasksService tasksService,UserService userService, UserMapper userMapper) {
        this.tasksService = tasksService;
        this.userService = userService;
        this.userMapper = userMapper;
    }

    @PostMapping
    public Tasks createTasks(@RequestBody Tasks newTask, @PathVariable Long id) throws UserNotFoundException {
        User user = userMapper.toEntity(userService.getUser(id));
        newTask.setUser(user);
        return tasksService.createTasks(newTask);
    }

    @GetMapping
    public List<Tasks> getAllTasks(@PathVariable Long id) {
        return tasksService.getAllTasks(id);
    }

    @GetMapping("/{idTask}")
    public Tasks getTaskById(@PathVariable Long idTask) {
        return tasksService.getTasksById(idTask);
    }
}
