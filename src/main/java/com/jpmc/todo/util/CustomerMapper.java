package com.jpmc.todo.util;

import com.jpmc.todo.dto.CustomerDTO;
import com.jpmc.todo.model.CustomerEntity;
import org.mapstruct.Mapper;

@Mapper
public interface CustomerMapper {
    CustomerDTO toDTO(CustomerEntity customerEntity);
    CustomerEntity toEntity(CustomerDTO customerDTO);
}

