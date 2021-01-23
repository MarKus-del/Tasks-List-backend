package com.markusdel.todolist.service;

import com.markusdel.todolist.dto.ListTasksDTO;
import com.markusdel.todolist.dto.TaskCreateDTO;
import com.markusdel.todolist.dto.TaskResponseDTO;
import com.markusdel.todolist.dto.UserResponseDTO;
import com.markusdel.todolist.exception.UserNotFoundException;
import com.markusdel.todolist.mapper.TasksMapper;
import com.markusdel.todolist.mapper.UserMapper;
import com.markusdel.todolist.model.Tasks;
import com.markusdel.todolist.model.User;
import com.markusdel.todolist.repository.TasksRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class TasksService {

    private TasksRepository tasksRepository;
    private UserService userService;
    private TasksMapper tasksMapper;
    private UserMapper userMapper;

    public TasksService(TasksRepository tasksRepository, TasksMapper tasksMapper, UserService userService, UserMapper userMapper) {
        this.tasksRepository = tasksRepository;
        this.tasksMapper = tasksMapper;
        this.userService = userService;
        this.userMapper = userMapper;
    }

    public List<TaskResponseDTO> getAllTasks(Long idUser) throws UserNotFoundException {
        UserResponseDTO user = userService.getUser(idUser);
        List<Tasks> allByUserId = tasksRepository.findAllByUserId(idUser);
        List<TaskResponseDTO> listTasksDTO = allByUserId.stream()
                .map(tasksMapper::toTaskResponseDTO)
                .collect(Collectors.toList());

        return listTasksDTO;
    }

    public TaskResponseDTO getTasksById(Long id) {
        Optional<Tasks> byId = tasksRepository.findById(id);

        return tasksMapper.toTaskResponseDTO(byId.get());
    }

    public TaskResponseDTO createTasks(TaskCreateDTO newTasks, Long idUser) throws UserNotFoundException {
        User user = userMapper.toEntity(userService.getUser(idUser));
        Tasks tasks = tasksMapper.toEntity(newTasks);
        tasks.setUser(user);
        return tasksMapper.toTaskResponseDTO(tasksRepository.save(tasks));
    }
}
