package com.markusdel.todolist.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TaskCreateDTO {

    @NotBlank
    @Size(max = 30)
    private String title;

    @NotBlank
    @Size(max = 60)
    private String description;
}
