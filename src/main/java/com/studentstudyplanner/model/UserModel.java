package com.studentstudyplanner.model;

import java.util.Set;

import jakarta.validation.constraints.NotBlank;

public class UserModel {

    private Long id;
    
    @NotBlank(message="Username is required")
    private String username;
    
    @NotBlank(message="Password is required")
    private String password;

    public UserModel() {
    }

    public UserModel(Long id, String username, String password, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Set<String> roles) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    // Getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
