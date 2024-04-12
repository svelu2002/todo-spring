package com.jpmc.todo.dto;

import java.time.LocalDate;

public record UserProfileDTO(int id, String fullName, String gender, LocalDate dob) implements BaseDTO {
}
