package com.jpmc.todo.dto;

import java.time.LocalDate;

public record OrderDTO(int id, String customerName, LocalDate orderDate) implements BaseDTO {
}
