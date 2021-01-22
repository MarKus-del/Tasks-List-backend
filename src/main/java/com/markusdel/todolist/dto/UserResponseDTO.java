package com.markusdel.todolist.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDTO {

    @NotBlank
    private Long id;

    @NotNull
    @Size(max = 60)
    private String name;

    @NotNull
    @Size(max = 60)
    private String lastName;

    @NotNull
    private String email;
}
