package com.markusdel.todolist.resource;

import com.markusdel.todolist.dto.ListTasksDTO;
import com.markusdel.todolist.dto.TaskCreateDTO;
import com.markusdel.todolist.dto.TaskResponseDTO;
import com.markusdel.todolist.dto.UserResponseDTO;
import com.markusdel.todolist.exception.TaskNotFoundException;
import com.markusdel.todolist.exception.UserNotFoundException;
import com.markusdel.todolist.mapper.UserMapper;
import com.markusdel.todolist.model.Tasks;
import com.markusdel.todolist.model.User;
import com.markusdel.todolist.service.TasksService;
import com.markusdel.todolist.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<TaskResponseDTO> createTasks(@RequestBody @Valid TaskCreateDTO newTask, @PathVariable Long id) throws UserNotFoundException {
        TaskResponseDTO tasks = tasksService.createTasks(newTask, id);
        return ResponseEntity.status(HttpStatus.CREATED).body(tasks);
    }

    @GetMapping
    public ResponseEntity<List<TaskResponseDTO>> getAllTasks(@PathVariable Long id) throws UserNotFoundException {
        List<TaskResponseDTO> allTasks = tasksService.getAllTasks(id);
        return ResponseEntity.ok(allTasks);
    }

    @GetMapping("/{idTask}")
    public ResponseEntity<TaskResponseDTO> getTaskById(@PathVariable Long idTask, @PathVariable(name="id") Long idUser) throws TaskNotFoundException {
        TaskResponseDTO tasksById = tasksService.getTasksById(idTask, idUser);
        return ResponseEntity.ok(tasksById);
    }

    @PutMapping("/{idTask}")
    public ResponseEntity<TaskResponseDTO> updateTaskById(@PathVariable long idTask, @RequestBody TaskCreateDTO newTask) throws TaskNotFoundException {
        TaskResponseDTO taskResponseDTO = tasksService.updateTask(idTask, newTask);
        return ResponseEntity.ok(taskResponseDTO);
    }

    @DeleteMapping("/{idTask}")
    public ResponseEntity deleteTaskById(@PathVariable long idTask) throws TaskNotFoundException {
        tasksService.deleteTask(idTask);
        return ResponseEntity.noContent().build();
    }
}
