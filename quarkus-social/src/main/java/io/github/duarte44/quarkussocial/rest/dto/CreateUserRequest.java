package io.github.duarte44.quarkussocial.rest.dto;

import lombok.Data;

@Data
public class CreateUserRequest {

    private String name;
    private Integer age;
}
