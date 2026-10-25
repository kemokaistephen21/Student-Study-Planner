
package com.studentstudyplanner.model;

import java.util.Set;

import jakarta.validation.constraints.NotBlank;

/**
 * Represents user account information used in the
 * Student Study Planner application.
 *
 * This model stores the user's ID, username, and password.
 * Validation annotations identify required account fields.
 */
public class UserModel {

    /**
     * Unique ID used to identify the user.
     */
    private Long id;
    
    /**
     * Username associated with the user account.
     * This field cannot be blank.
     */
    @NotBlank(message="Username is required")
    private String username;
    
    /**
     * Password associated with the user account.
     * This field cannot be blank.
     */
    @NotBlank(message="Password is required")
    private String password;

    /**
     * Creates an empty UserModel.
     *
     * This constructor allows the application to create
     * a user model before assigning account information.
     */
    public UserModel() {
    }

    /**
     * Creates a UserModel with the provided account information.
     *
     * Only the ID, username, and password are assigned
     * to fields by this constructor. The remaining parameters
     * are accepted but are not stored in this model.
     *
     * @param id unique ID of the user
     * @param username username associated with the account
     * @param password password associated with the account
     * @param enabled whether the user account is enabled
     * @param accountNonExpired whether the account has not expired
     * @param credentialsNonExpired whether the credentials have not expired
     * @param accountNonLocked whether the account is not locked
     * @param roles set of roles associated with the user
     */
    public UserModel(Long id, String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Set<String> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    // Getters and setters

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
     * Retrieves the password associated with the account.
     *
     * @return the password value
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the password associated with the account.
     *
     * @param password the password value to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
}
