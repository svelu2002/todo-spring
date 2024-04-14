package com.jpmc.todo.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerEntity implements BaseEntity {
    private int id;
    private String name;
    private String email;

}
