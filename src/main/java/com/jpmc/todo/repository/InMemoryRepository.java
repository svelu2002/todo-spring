package com.jpmc.todo.repository;

import com.jpmc.todo.model.BaseEntity;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Random;

public interface InMemoryRepository<T extends BaseEntity> {
//
//    Map<Integer, T> dataStore = new HashMap<>();
//    Random random = new Random();
//
//    default T save(T entity) {
//        Integer id = entity.getId();
//        if (id == null || !dataStore.containsKey(id)) {
//            id = generateId();
//            entity.setId(id);
//        }
//        dataStore.put(entity.getId(), entity);
//        return entity;
//    }
//
//    default Iterable<T> findAll() {
//        return dataStore.values();
//    }
//
//    default Optional<T> findById(Integer id) {
//        return Optional.ofNullable(dataStore.get(id));
//    }
//
//    default void delete(Integer id) {
//        dataStore.remove(id);
//    }
//
//    // Helper method to generate unique IDs using Java 17's Random generator
//    private Integer generateId() {
//        return random.nextInt();
//    }
}

