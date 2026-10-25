
package com.studentstudyplanner.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

/**
 * Represents a user account stored in the Student Study Planner database.
 *
 * This entity contains the user's ID, username, and password.
 * The password is stored as a hash when it is saved through
 * the application's UserService.
 */
@Entity
@Table(name = "users")
public class UserEntity {

    /**
     * Unique ID used to identify the user in the database.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    /**
     * Username used to identify the user's account.
     */
    @Column(name = "name")
    private String username;

    /**
     * Password value stored for the user account.
     * The application hashes passwords before saving new users.
     */
    @Column(name = "password")
    private String password;

    /**
     * Creates an empty UserEntity.
     *
     * This constructor allows JPA to create
     * user entity objects when retrieving database records.
     */
    public UserEntity() {
    }

    /**
     * Creates a UserEntity with the provided account information.
     *
     * @param id unique ID of the user
     * @param username username associated with the account
     * @param password password value associated with the account
     */
    public UserEntity(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    /**
     * Retrieves the unique ID of the user.
     *
     * @return the user ID
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets the unique ID of the user.
     *
     * @param id the user ID to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Retrieves the username associated with the account.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the username associated with the account.
     *
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Retrieves the stored password value for the user.
     *
     * @return the stored password value
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password value for the user account.
     *
     * @param password the password value to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}

