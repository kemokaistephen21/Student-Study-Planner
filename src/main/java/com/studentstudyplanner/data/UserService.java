
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

/**
 * Provides services for managing user accounts in the
 * Student Study Planner application.
 *
 * This class handles user registration, password hashing,
 * account information, and user authentication.
 * It also converts user information between models and entities.
 */
@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // added to hash passwords

    /**
     * Creates a UserService instance with the required
     * repository and password encoder.
     *
     * @param userRepository repository used to manage user accounts
     * @param passwordEncoder encoder used to hash user passwords
     */
    // two beans are injected into the constructor: UserRepository and PasswordEncoder
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Saves a user account in the database.
     *
     * Hashes the user's password before converting the
     * user model into an entity and saving it.
     *
     * @param userModel user information to save
     * @return the saved user information as a UserModel
     */
    public UserModel save(UserModel userModel) {
        // use the passwordEncoder to hash the password before saving it to the database
        userModel.setPassword(passwordEncoder.encode(userModel.getPassword()));
        UserEntity userEntity = convertToEntity(userModel);
        UserEntity savedUser = userRepository.save(userEntity);
        return convertToModel(savedUser);
    }

    /**
     * Loads a user account using the provided username.
     *
     * This method is used by Spring Security during
     * the authentication process.
     *
     * @param username username of the account to find
     * @return the user's security details, or null if
     *         the username is not found
     * @throws UsernameNotFoundException declared by the
     *         UserDetailsService interface
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findByUsername(username).orElse(null);
        if (userEntity == null) {
            return null;
        }
        return new CustomUserDetails(userEntity);
    }

    /**
     * Finds a user account using its database ID.
     *
     * @param id ID of the user to find, provided as a String
     * @return the matching user information as a UserModel
     */
    public UserModel findById(String id) {
        UserEntity userEntity = userRepository.findById(Long.parseLong(id)).orElse(null);
        return convertToModel(userEntity);
    }

    /**
     * Deletes a user account using its database ID.
     *
     * @param id ID of the user to delete, provided as a String
     */
    public void delete(String id) {
        userRepository.deleteById(Long.parseLong(id));
    }

    /**
     * Retrieves all user accounts stored in the database.
     *
     * Converts the user entities into user models.
     *
     * @return a list of user models
     */
    public List<UserModel> findAll() {
        List<UserEntity> userEntities = userRepository.findAll();
        List<UserModel> userModels = convertToModels(userEntities);
        return userModels;
    }

    /**
     * Checks whether a username already exists in the database.
     *
     * This method helps prevent duplicate usernames
     * during user registration.
     *
     * @param username username to check
     * @return true if the username exists, otherwise false
     */
    public boolean usernameExists(String username)
    {
        return userRepository.existsByUsername(username);
    }

    /**
     * Converts a list of user entities into user models.
     *
     * @param userEntities list of user entities to convert
     * @return a list containing the converted user models
     */
    public List<UserModel> convertToModels(List<UserEntity> userEntities) {
        List<UserModel> userModels = new ArrayList<>();
        for (UserEntity userEntity : userEntities) {
            userModels.add(convertToModel(userEntity));
        }
        return userModels;
    }

    /**
     * Converts a UserEntity into a UserModel.
     *
     * Copies the user's ID, username, and stored
     * password hash into the model.
     *
     * @param userEntity user entity to convert
     * @return a UserModel containing the user's information
     */
    public UserModel convertToModel(UserEntity userEntity) {
        UserModel userModel = new UserModel();
        userModel.setId(userEntity.getId());
        userModel.setUsername(userEntity.getUsername());
        userModel.setPassword(userEntity.getPassword());
        return userModel;
    }

    /**
     * Converts a UserModel into a UserEntity.
     *
     * Copies the user's ID, username, and password
     * into an entity for database operations.
     *
     * @param userModel user model to convert
     * @return a UserEntity containing the user's information
     */
    private UserEntity convertToEntity(UserModel userModel) {
        UserEntity userEntity = new UserEntity();
        if (userModel.getId() != null) {
            userEntity.setId(userModel.getId());
        }
        userEntity.setUsername(userModel.getUsername());
        userEntity.setPassword(userModel.getPassword());
        return userEntity;
    }

    /**
     * Retrieves the details of the currently authenticated user.
     *
     * Checks the Spring Security context to make sure
     * a user is logged in and is not anonymous.
     *
     * @return the currently authenticated user's details
     * @throws IllegalStateException if no authenticated
     *         user is found in the current session
     */
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

