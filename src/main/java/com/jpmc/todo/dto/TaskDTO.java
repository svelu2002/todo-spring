package com.jpmc.todo.dto;

import java.util.List;
import java.util.Optional;

public record TaskDTO(int id, String title, String description, Optional<List<StepDTO>> steps) implements BaseDTO {
}
