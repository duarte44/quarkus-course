package io.github.duarte44.quarkussocial.rest.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class CreateUserRequest {

    @NotBlank(message = "Name is Required") //não deixa ser nulo nem vazia
    private String name;

    @NotNull(message = "Age is Required") //não deixa ser nulo
    private Integer age;
}
