package com.studentstudyplanner.data;

import com.studentstudyplanner.data.repository.UserRepository;
import com.studentstudyplanner.data.entity.UserEntity;
import com.studentstudyplanner.model.CustomUserDetails;
import com.studentstudyplanner.model.UserModel;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository; 
    private final PasswordEncoder passwordEncoder; // added to hash passwords

    // two beans are injected into the constructor: UserRepository and PasswordEncoder
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    	this.userRepository = userRepository;
    	this.passwordEncoder = passwordEncoder;
    } 

    public UserModel save(UserModel userModel) { 
    	// use the passwordEncoder to hash the password before saving it to the database
    	userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
        UserEntity userEntity = convertToEntity(userModel);
        UserEntity savedUser = userRepository.save(userEntity);
        return convertToModel(savedUser);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    	UserEntity userEntity = userRepository.findByUsername(username).orElse(null);
    	if (userEntity == null) {
    		return null;
    	}
    	return new CustomUserDetails(userEntity);
    }

    public UserModel findById(String id) {
        UserEntity userEntity = userRepository.findById(Long.parseLong(id)).orElse(null);
        return convertToModel(userEntity);
    }

    public void delete(String id) {
        userRepository.deleteById(Long.parseLong(id));
    }

    public List<UserModel> findAll() {
        List<UserEntity> userEntities = userRepository.findAll();
        List<UserModel> userModels =  convertToModels(userEntities);
        return userModels;
    }  
    
    public boolean usernameExists(String username)
    {
    	return userRepository.existsByUsername(username);
    }

    public List<UserModel> convertToModels(List<UserEntity> userEntities) {
        List<UserModel> userModels = new ArrayList<>();
        for (UserEntity userEntity : userEntities) {
            userModels.add(convertToModel(userEntity));
        }
        return userModels;
    }
 
    public UserModel convertToModel(UserEntity userEntity) {
        UserModel userModel = new UserModel();
        userModel.setId(userEntity.getId());
        userModel.setUsername(userEntity.getUsername());
        userModel.setPassword(userEntity.getPassword());
        return userModel;
    }

    private UserEntity convertToEntity(UserModel userModel) {
        UserEntity userEntity = new UserEntity();
        if (userModel.getId() != null) {
            userEntity.setId(userModel.getId());
        }
        userEntity.setUsername(userModel.getUsername());
        userEntity.setPassword(userModel.getPassword());
        return userEntity;

    } 
   
    public CustomUserDetails getCurrentUserDetails() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        
        // Ensure there is an active authentication and it is not an anonymous user
        if (authentication != null && !(authentication instanceof AnonymousAuthenticationToken)) {
            Object principal = authentication.getPrincipal();
            
            if (principal instanceof UserDetails) {
                return (CustomUserDetails) principal;
            }
        }
        throw new IllegalStateException("No authenticated user found in session");
    }
}
