package com.jpmc.todo.dto;

public record TaskDTO(int id, String title, String description) implements BaseDTO {
}
