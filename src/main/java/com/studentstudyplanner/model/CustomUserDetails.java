package com.studentstudyplanner.model;

import java.util.Collection;
import java.util.Collections;

import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.studentstudyplanner.data.entity.UserEntity;

public class CustomUserDetails implements UserDetails {

	private final UserEntity userEntity;
	
	public CustomUserDetails(UserEntity userEntity) {
        this.userEntity = userEntity;
    }
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.emptyList();
	}

	public Long getId() {
        return userEntity.getId();
    }

	@Override
	public String getUsername() {
		return userEntity.getUsername();
	}
	
	@Override
	public @Nullable String getPassword() {
		return userEntity.getPassword();
	}
	
	@Override
    public boolean isAccountNonExpired() { return true; }

    @Override
    public boolean isAccountNonLocked() { return true; }

    @Override
    public boolean isCredentialsNonExpired() { return true; }

    @Override
    public boolean isEnabled() { return true; }

}
