package com.studentstudyplanner.data.repository;

import com.studentstudyplanner.data.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
	boolean existsByUsername(String username);
	Optional<UserEntity> findByUsername(String username);
}
