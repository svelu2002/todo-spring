package com.jpmc.todo.repository;

import com.jpmc.todo.model.UserProfileEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserProfileRepository extends JpaRepository<UserProfileEntity, Integer> {
    @Query("FROM user_profiles t WHERE t.gender=:gender")
    public List<UserProfileEntity> getUsersByGender(@Param("gender") String gender);
}
