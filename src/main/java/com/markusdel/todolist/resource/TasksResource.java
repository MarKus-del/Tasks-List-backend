package com.markusdel.todolist.resource;

import com.markusdel.todolist.dto.ListTasksDTO;
import com.markusdel.todolist.dto.TaskCreateDTO;
import com.markusdel.todolist.dto.TaskResponseDTO;
import com.markusdel.todolist.dto.UserResponseDTO;
import com.markusdel.todolist.exception.UserNotFoundException;
import com.markusdel.todolist.mapper.UserMapper;
import com.markusdel.todolist.model.Tasks;
import com.markusdel.todolist.model.User;
import com.markusdel.todolist.service.TasksService;
import com.markusdel.todolist.service.UserService;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public TaskResponseDTO createTasks(@RequestBody @Valid TaskCreateDTO newTask, @PathVariable Long id) throws UserNotFoundException {
        return tasksService.createTasks(newTask, id);
    }

    @GetMapping
    public List<TaskResponseDTO> getAllTasks(@PathVariable Long id) throws UserNotFoundException {
        return tasksService.getAllTasks(id);
    }

    @GetMapping("/{idTask}")
    public TaskResponseDTO getTaskById(@PathVariable Long idTask) {
        return tasksService.getTasksById(idTask);
    }
}
