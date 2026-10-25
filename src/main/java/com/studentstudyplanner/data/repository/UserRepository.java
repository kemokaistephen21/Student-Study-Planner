
package com.studentstudyplanner.data.repository;

import com.studentstudyplanner.data.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.*;

/**
 * Provides database operations for user account records.
 *
 * This repository extends Spring Data's JpaRepository
 * to support creating, retrieving, updating, and deleting
 * UserEntity objects stored in the database.
 *
 * It also provides methods for checking whether a username
 * exists and retrieving a user account by username.
 */
public interface UserRepository extends JpaRepository<UserEntity, Long> {

    /**
     * Checks whether a user account with the specified
     * username already exists in the database.
     *
     * @param username the username to check
     * @return true if the username exists, otherwise false
     */
    boolean existsByUsername(String username);

    /**
     * Searches the database for a user account
     * matching the specified username.
     *
     * @param username the username to search for
     * @return an Optional containing the matching UserEntity
     *         if found, or an empty Optional if no user exists
     */
    Optional<UserEntity> findByUsername(String username);
}

