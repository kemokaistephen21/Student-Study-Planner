
package com.studentstudyplanner.model;

import java.util.Collection;
import java.util.Collections;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.studentstudyplanner.data.entity.UserEntity;

/**
 * Provides user account information to Spring Security.
 *
 * This class implements the UserDetails interface to supply
 * authentication information from a UserEntity object.
 *
 * It provides access to the user's ID, username, password,
 * authorities, and account status.
 */
public class CustomUserDetails implements UserDetails {

    /**
     * User entity containing the account information.
     */
    private final UserEntity userEntity;

    /**
     * Creates a CustomUserDetails object using a user entity.
     *
     * @param userEntity the user entity containing account information
     */
    public CustomUserDetails(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    /**
     * Retrieves the authorities assigned to the user.
     *
     * Currently returns an empty collection because
     * no authorities are assigned by this class.
     *
     * @return an empty collection of granted authorities
     */
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.emptyList();
    }

    /**
     * Retrieves the unique ID of the user.
     *
     * @return the user ID
     */
    public Long getId() {
        return userEntity.getId();
    }

    /**
     * Retrieves the username used for authentication.
     *
     * @return the username associated with the user account
     */
    @Override
    public String getUsername() {
        return userEntity.getUsername();
    }

    /**
     * Retrieves the stored password value for authentication.
     *
     * @return the user's stored password value, or null
     *         if no password is available
     */
    @Override
    public @Nullable String getPassword() {
        return userEntity.getPassword();
    }

    /**
     * Checks whether the user account has not expired.
     *
     * @return true because this implementation treats
     *         all user accounts as not expired
     */
    @Override
    public boolean isAccountNonExpired() { return true; }

    /**
     * Checks whether the user account is not locked.
     *
     * @return true because this implementation treats
     *         all user accounts as unlocked
     */
    @Override
    public boolean isAccountNonLocked() { return true; }

    /**
     * Checks whether the user's credentials have not expired.
     *
     * @return true because this implementation treats
     *         all user credentials as not expired
     */
    @Override
    public boolean isCredentialsNonExpired() { return true; }

    /**
     * Checks whether the user account is enabled.
     *
     * @return true because this implementation treats
     *         all user accounts as enabled
     */
    @Override
    public boolean isEnabled() { return true; }

}
