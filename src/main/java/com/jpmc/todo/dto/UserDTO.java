package com.jpmc.todo.dto;

public record UserDTO(int id, String username, String password, String role) implements BaseDTO {
}
