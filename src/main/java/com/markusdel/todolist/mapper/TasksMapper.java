package com.markusdel.todolist.mapper;

import com.markusdel.todolist.dto.TaskCreateDTO;
import com.markusdel.todolist.dto.TaskResponseDTO;
import com.markusdel.todolist.dto.UserCreateDTO;
import com.markusdel.todolist.dto.UserResponseDTO;
import com.markusdel.todolist.model.Tasks;
import com.markusdel.todolist.model.User;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class TasksMapper {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public Tasks toEntity (TaskCreateDTO taskCreateDTO) {
        return  MODEL_MAPPER.map(taskCreateDTO, Tasks.class);
    }

    public TaskResponseDTO toTaskResponseDTO(Tasks task) {
        return MODEL_MAPPER.map(task, TaskResponseDTO.class);
    }
}
